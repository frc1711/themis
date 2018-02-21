package org.usfirst.frc.team1711.robot.commands.lift;

import org.usfirst.frc.team1711.robot.OI;
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
    	Robot.lift.runLift(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(Math.abs(OI.auxStick.getRawAxis(1)) > .1)
    	{
    		/*if(!Robot.lift.getTopLimitSwitch() && OI.auxStick.getRawAxis(1) > 0)
    			Robot.lift.runLift(OI.auxStick.getRawAxis(1));
    		else if(!Robot.lift.getBottomLimitSwitch() && OI.auxStick.getRawAxis(1) < 0)
    			Robot.lift.runLift(OI.auxStick.getRawAxis(1));
    		else
    			Robot.lift.runLift(0); */
    		Robot.lift.runLift(OI.auxStick.getRawAxis(1));
    	}
    	else
    		Robot.lift.runLift(0);
    	
    	Robot.lift.runLift(OI.auxStick.getRawAxis(1));
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
