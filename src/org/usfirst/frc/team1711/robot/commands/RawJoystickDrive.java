package org.usfirst.frc.team1711.robot.commands;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RawJoystickDrive extends Command 
{
	DriveSystem.DriveType type;

    public RawJoystickDrive() 
    {
        requires(Robot.driveSystem);
        
        type = Robot.driveSystem.type;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	switch(type)
    	{
	    	case MECANUM:
	    		Robot.driveSystem.mecanumDriving(RobotMap.driveStick.getMagnitude(), RobotMap.driveStick.getDirectionRadians(), RobotMap.driveStick.getRawAxis(RobotMap.throttleAxis));
	    	case DIFFERENTIAL:
	    		Robot.driveSystem.arcadeDriving();
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
