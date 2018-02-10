package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;

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
		rightIntakeTalon.set(ControlMode.PercentOutput, 0.50);
		leftIntakeTalon.set(ControlMode.PercentOutput, 0.30);
	}
	
	public void expel()
	{
		rightIntakeTalon.set(ControlMode.PercentOutput, -0.50);
		leftIntakeTalon.set(ControlMode.PercentOutput, -0.30);
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
    }
}

