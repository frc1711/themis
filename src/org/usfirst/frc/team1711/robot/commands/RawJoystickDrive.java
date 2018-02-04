package org.usfirst.frc.team1711.robot.commands;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.GenericHID;
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
 //   	Robot.driveSystem.enableLoadProfiling();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
/*    	if((Math.abs(RobotMap.driveStick.getRawAxis(1)) > 0.08) || (Math.abs(RobotMap.driveStick.getRawAxis(0)) > 0.08))
    	{
    		switch(type)
        	{
    	    	case MECANUM:
    	    		Robot.driveSystem.mecanumDriving(RobotMap.driveStick.getRawAxis(RobotMap.throttleAxis),
    	    				RobotMap.driveStick.getDirectionRadians(),
    	    				RobotMap.driveStick.getRawAxis(0));
    	    	case DIFFERENTIAL:
    	    		Robot.driveSystem.arcadeDriving();
        	}
    	} */
    	
    	//Robot.driveSystem.cartesianDrive();
    	Robot.driveSystem.polarDrive(RobotMap.driveStick.getAngle(GenericHID.Hand.kLeft),
    			RobotMap.driveStick.getMagnitude(GenericHID.Hand.kLeft),
    			RobotMap.driveStick.getRawAxis(4));
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
