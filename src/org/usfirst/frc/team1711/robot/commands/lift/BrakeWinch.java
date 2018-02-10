package org.usfirst.frc.team1711.robot.commands.lift;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BrakeWinch extends Command {

    public BrakeWinch() 
    {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.lift.brake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(!Robot.lift.brakeSwitch.get())
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.lift.stopBrake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.lift.stopBrake();
    }
}
