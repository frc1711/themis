package org.usfirst.frc.team1711.robot.commands.lift;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetBrake extends Command 
{
	int angle;
	int count;

    public SetBrake(int desiredAngle) 
    {
    	requires(Robot.brakeSystem);
        angle = desiredAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.brakeSystem.setServo(angle);
    	count++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(angle <= 40 && count > 10)
    		return true; 
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.brakeSystem.chill();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
