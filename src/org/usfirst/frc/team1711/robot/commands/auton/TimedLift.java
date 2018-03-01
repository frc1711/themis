package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedLift extends TimedCommand 
{
	double speed;

    public TimedLift(double timeout, double speed) {
        super(timeout);
        requires(Robot.lift);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.runLift(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.runLift(speed);
    }

    // Called once after timeout
    protected void end() {
    	Robot.lift.runLift(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.runLift(0);
    }
}
