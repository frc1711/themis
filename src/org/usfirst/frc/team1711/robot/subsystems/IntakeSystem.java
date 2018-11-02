package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.OI;
import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.lift.PowerWinch;
import org.usfirst.frc.team1711.robot.commands.intake.Expel;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSystem extends Subsystem 
{
	public static TalonSRX rightIntakeTalon;
	public static TalonSRX leftIntakeTalon;
	
	public IntakeSystem()
	{
		rightIntakeTalon = new TalonSRX(RobotMap.rightIntake);
		leftIntakeTalon = new TalonSRX(RobotMap.leftIntake);
	}
	
	public void intake()
	{
		rightIntakeTalon.set(ControlMode.PercentOutput, -0.75);
		leftIntakeTalon.set(ControlMode.PercentOutput, 0.75);
	}
	
	public void expel(double speed)
	{	
		rightIntakeTalon.set(ControlMode.PercentOutput, speed);
		leftIntakeTalon.set(ControlMode.PercentOutput, speed);
	}
	
	public void drop()
	{
		rightIntakeTalon.set(ControlMode.PercentOutput, 0.5);
		leftIntakeTalon.set(ControlMode.PercentOutput, -0.5);
	}
	
	public void stop()
	{
		rightIntakeTalon.set(ControlMode.PercentOutput, 0);
		leftIntakeTalon.set(ControlMode.PercentOutput, 0);
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Expel());    
    	}
}

