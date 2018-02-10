package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroDriveAuton extends Command 
{
	
	int angle;

    public GyroDriveAuton(int angle) 
    {
        requires(Robot.driveSystem);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(angle > 0)
    	{
    		if(Robot.driveSystem.gyro.getAngle() < angle)
    			Robot.driveSystem.turn(angle, 0.5);
    		Robot.driveSystem.stopRobot();
    	}
    	else if(angle < 0)
    	{
    		if(Robot.driveSystem.gyro.getAngle() > angle)
    			Robot.driveSystem.turn(angle, 0.5);
    		Robot.driveSystem.stopRobot();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.driveSystem.stopRobot();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.driveSystem.stopRobot();
    }
}
