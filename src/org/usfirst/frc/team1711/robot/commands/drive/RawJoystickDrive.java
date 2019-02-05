package org.usfirst.frc.team1711.robot.commands.drive;

import org.usfirst.frc.team1711.robot.OI;
import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RawJoystickDrive extends Command {

    public RawJoystickDrive() 
    {
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.driveSystem.stopRobot();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(OI.driveStick.getMagnitude() > RobotMap.XBOX_DEADZONE || Math.abs(OI.driveStick.getRawAxis(4)) > RobotMap.XBOX_DEADZONE)
    		Robot.driveSystem.arcadeDrive(-1 * OI.driveStick.getRawAxis(1), OI.driveStick.getRawAxis(4));
    	else
    		Robot.driveSystem.arcadeDrive(0, 0);
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
