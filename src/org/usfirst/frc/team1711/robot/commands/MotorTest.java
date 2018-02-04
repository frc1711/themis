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
	TalonSRX[] motors;

    public MotorTest(TalonSRX motor, TalonSRX motor2, TalonSRX motor3, TalonSRX motor4) 
    {
        requires(Robot.driveSystem);
        motors = new TalonSRX[4];
        motors[0] = motor;
        motors[1] = motor2;
        motors[2] = motor3;
        motors[3] = motor4;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	for(int i = 0; i < 4; i++)
    	{
    		motors[i].set(ControlMode.PercentOutput, 0);
    		
    		if((motors[i] == Robot.driveSystem.frontRightDrive) || (motors[i] == Robot.driveSystem.rearRightDrive))
        		motors[i].setInverted(true);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	motors[0].set(ControlMode.PercentOutput, 0.5);
    	motors[1].set(ControlMode.PercentOutput, 0.5);
    	motors[2].set(ControlMode.PercentOutput, 0.5);
    	motors[3].set(ControlMode.PercentOutput, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	motors[0].set(ControlMode.PercentOutput, 0);
    	motors[1].set(ControlMode.PercentOutput, 0);
    	motors[2].set(ControlMode.PercentOutput, 0);
    	motors[3].set(ControlMode.PercentOutput, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	motors[0].set(ControlMode.PercentOutput, 0);
    	motors[1].set(ControlMode.PercentOutput, 0);
    	motors[2].set(ControlMode.PercentOutput, 0);
    	motors[3].set(ControlMode.PercentOutput, 0);
    }
}
