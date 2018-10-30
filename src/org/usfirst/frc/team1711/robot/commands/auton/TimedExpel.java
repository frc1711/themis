package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedExpel extends TimedCommand {

    public TimedExpel(double timeout) {
        super(timeout);
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.intake.stop();
    	System.out.println("TimedExpel is being initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.expel(-.075);
    }

    // Called once after timeout
    protected void end() {
    	Robot.intake.stop();
    	System.out.println("TimedExpel is ending");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stop();
    	System.out.println("TimedExpel was interrupted");
    }
}
