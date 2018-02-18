package org.usfirst.frc.team1711.robot.commands.drive;

import org.usfirst.frc.team1711.robot.OI;
import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OrthoSwitchDrive extends Command {

    public OrthoSwitchDrive() 
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
    	double x = -1 * OI.driveStick.getRawAxis(1);
    	double y = -1 * OI.driveStick.getRawAxis(0);
    	double stickRotation = OI.driveStick.getRawAxis(4);
    	double gyroAngle = Robot.driveSystem.gyro.getAngle();
    	
    	if(OI.driveStick.getMagnitude() > RobotMap.XBOX_DEADZONE || Math.abs(stickRotation) > 0.2)
    	{
    		if(isOrthoMode())
    		{
    			 double rotationLockOutput = -1 * gyroAngle/50;
    			 Robot.driveSystem.cartesianDrive(x, y, rotationLockOutput);
    		}
    		else
    			Robot.driveSystem.cartesianDrive(x, y, stickRotation);
    	}
    	else
    		Robot.driveSystem.cartesianDrive(0, 0, 0);
    }
    
    private boolean isOrthoMode()
    {
    	if(Math.abs(OI.driveStick.getRawAxis(4)) > 0.2)
    		return false;
    	else
    		return true;
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
