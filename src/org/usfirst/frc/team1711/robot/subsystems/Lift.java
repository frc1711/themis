package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem 
{
    TalonSRX liftTalon;
    
    DigitalInput topLimitSwitch;
    DigitalInput bottomLimitSwitch;
    
    public Lift()
    {
    	liftTalon = new TalonSRX(RobotMap.liftMotor);
    	liftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
    	topLimitSwitch = new DigitalInput(RobotMap.topLiftSwitch);
    	bottomLimitSwitch = new DigitalInput(RobotMap.bottomLiftSwitch);
    }
    
    public void runLift(double speed)
    {
    	//percent output has a range of -1 to 1
    	liftTalon.set(ControlMode.PercentOutput, speed);
    }
    
    public void setPositionMode(int setPoint)
    {
    	liftTalon.set(ControlMode.Position, setPoint);
    }
    
    public int getLiftPosition()
    {
    	return liftTalon.getSelectedSensorPosition(0);
    }
    
    public boolean getTopLimitSwitch()
    {
    	return topLimitSwitch.get();
    }
    
    public boolean getBottomLimitSwitch()
    {
    	return bottomLimitSwitch.get();
    }

    public void initDefaultCommand() 
    {
        
    }
}

