package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftGoToSetPoint extends Command 
{
	double setPoint;

    public LiftGoToSetPoint(double setPoint) 
    {
        requires(Robot.lift);
        this.setPoint = setPoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.lift.runLift(0);
    	System.out.println("LiftGoToSetPoint is being initialized");
//    	Robot.lift.zeroLiftEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.lift.runLift(-0.7);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(Robot.lift.getLiftEncoder() >= setPoint)
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.lift.runLift(0);
    	System.out.println("LiftGoToSetPoint is ending");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.lift.runLift(0);
    	System.out.println("LiftGoToSetPoint was interrupted");
    }
}
