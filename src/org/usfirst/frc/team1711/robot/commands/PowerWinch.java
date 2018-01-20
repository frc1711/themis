package org.usfirst.frc.team1711.robot.commands;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PowerWinch extends Command 
{
	boolean lockMode = true;
	boolean lockActive;
	int setPoint;

    public PowerWinch() 
    {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	lockActive = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(lockMode && Math.abs(RobotMap.auxStick.getRawAxis(RobotMap.throttleAxis)) < 0.1)
    	{
    		setPoint = Robot.lift.getLiftPosition();
    		Robot.lift.setPositionMode(setPoint);
    		lockActive = true;
    	}
    	
    	else
    	{
    		lockActive = false;
    		
    		if((RobotMap.auxStick.getRawAxis(RobotMap.throttleAxis) > 0.1) && !Robot.lift.getTopLimitSwitch())
    		{
    			Robot.lift.runLift(RobotMap.auxStick.getRawAxis(RobotMap.throttleAxis));
    		}
    		
    		else if((RobotMap.auxStick.getRawAxis(RobotMap.throttleAxis) < -0.1) && !Robot.lift.getBottomLimitSwitch())
    		{
    			Robot.lift.runLift(RobotMap.auxStick.getRawAxis(RobotMap.throttleAxis));
    		}
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
    	Robot.lift.runLift(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.lift.runLift(0);
    }
}
