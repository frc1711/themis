package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.lift.PowerWinch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem 
{
    TalonSRX liftTalon;
    TalonSRX otherLiftTalon;
    TalonSRX brakeTalon;
    
    DigitalInput topLimitSwitch;
    DigitalInput bottomLimitSwitch;
    public DigitalInput brakeSwitch;
    
    public Lift()
    {
    	liftTalon = new TalonSRX(RobotMap.liftMotor);
    	otherLiftTalon = new TalonSRX(RobotMap.otherLiftMotor);
    	liftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
    	brakeTalon = new TalonSRX(RobotMap.brakeMotor);
    	topLimitSwitch = new DigitalInput(RobotMap.topLiftSwitch);
    	bottomLimitSwitch = new DigitalInput(RobotMap.bottomLiftSwitch);
    	brakeSwitch = new DigitalInput(RobotMap.brakeSwitch);
    }
    
    public void runLift(double speed)
    {
    	//percent output has a range of -1 to 1
    	liftTalon.set(ControlMode.PercentOutput, speed);
    	otherLiftTalon.set(ControlMode.Follower, RobotMap.liftMotor);
    }
    
    public void setPositionMode(double setPoint)
    {
    	//the set point is in native rotation units
    	liftTalon.set(ControlMode.Position, setPoint);
    }
    
    public int getLiftPosition()
    {
    	return liftTalon.getSelectedSensorPosition(0);
    }
    
    public void brake()
    {
    	brakeTalon.set(ControlMode.PercentOutput, 0.5);
    }
    
    public void stopBrake()
    {
    	brakeTalon.set(ControlMode.PercentOutput, 0);
    }
    
    public boolean getTopLimitSwitch()
    {
    	return topLimitSwitch.get();
    }
    
    public boolean getBottomLimitSwitch()
    {
    	return bottomLimitSwitch.get();
    }
    
    public double getLiftEncoder()
    {
    	return liftTalon.getSensorCollection().getQuadraturePosition();
    }
    
    public void zeroLiftEncoder()
    {
    	//second number is timeout in ms
    	liftTalon.getSensorCollection().setQuadraturePosition(0, 15);
    }
    
    public void printOutput(int setting)
    {
    	switch(setting)
    	{
    	case 0: 
    		//System.out.println("Lift encoder: " + getLiftEncoder());
    		SmartDashboard.putNumber("Lift encoder", getLiftEncoder());
    	case 1:
    		//System.out.println("Motor 1 current: " + liftTalon.getOutputCurrent());
    		//System.out.println("Motor 2 current: " + otherLiftTalon.getOutputCurrent());
    		SmartDashboard.putNumber("Motor 1 current", liftTalon.getOutputCurrent());
    		SmartDashboard.putNumber("Motor 2 current", otherLiftTalon.getOutputCurrent());
    	}
    }

    public void initDefaultCommand() 
    {
    	setDefaultCommand(new PowerWinch());
    }
}

