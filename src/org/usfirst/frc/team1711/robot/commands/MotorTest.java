package org.usfirst.frc.team1711.robot.commands;

import org.usfirst.frc.team1711.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorTest extends Command 
{
	TalonSRX motor;

    public MotorTest(TalonSRX motor) 
    {
        requires(Robot.driveSystem);
        this.motor = motor;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	motor.set(ControlMode.PercentOutput, 0);
    	
    	if((motor == Robot.driveSystem.frontRightDrive) || (motor == Robot.driveSystem.rearRightDrive))
    		motor.setInverted(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	motor.set(ControlMode.PercentOutput, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	motor.set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	motor.set(ControlMode.PercentOutput, 0);
    }
}
