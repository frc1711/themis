package org.usfirst.frc.team1711.robot.commands;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftEncoderCalibration extends Command 
{
	double liftEncoderValue;
	double pulses;
	
    public LiftEncoderCalibration(double pulses) 
    {
        requires(Robot.lift);
        this.pulses = pulses;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.lift.runLift(0);
    	Robot.lift.zeroLiftEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	liftEncoderValue = Robot.lift.getLiftEncoder();
    	Robot.lift.runLift(0.25);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	//let's get up working first then we'll worry about down
    	if(liftEncoderValue < pulses)
    		return false;
    	else
    		return true;
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
