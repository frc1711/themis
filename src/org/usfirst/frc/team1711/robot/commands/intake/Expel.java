package org.usfirst.frc.team1711.robot.commands.intake;

import org.usfirst.frc.team1711.robot.OI;
import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Expel extends Command {

    public Expel() 
    {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if(Math.abs(OI.throwStick.getRawAxis(5)) > .1)
    	{
    		
    		Robot.intake.expel(OI.throwStick.getRawAxis(5));
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
    	Robot.intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.intake.stop();
    }
}
