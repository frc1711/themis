package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedIntake extends TimedCommand {

    public TimedIntake(double timeout) 
    {
        super(timeout);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.intake.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    }

    // Called once after timeout
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
