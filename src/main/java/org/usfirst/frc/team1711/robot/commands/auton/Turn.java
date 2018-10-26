package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	double desiredTurnAngle;
	double robotAngle;
	
    public Turn(double angle) 
    {
        requires(Robot.driveSystem);
        this.desiredTurnAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.zeroGyro();
    	System.out.println("Turn is being initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	robotAngle = Robot.driveSystem.getGyroAngle();
 /*   	if(!Robot.driveSystem.gyro.isConnected())
    		Robot.driveSystem.stopRobot();
    	else */
    		Robot.driveSystem.turn(desiredTurnAngle, 0.35);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
  /*  	if(!(desiredTurnAngle > 0 && robotAngle < desiredTurnAngle) || !(desiredTurnAngle < 0 && robotAngle > desiredTurnAngle))
    		return true; */
    	if(Robot.driveSystem.getGyroAngle() >= desiredTurnAngle && desiredTurnAngle > 0)
    		return true;
    	else if(Robot.driveSystem.getGyroAngle() <= desiredTurnAngle && desiredTurnAngle < 0)
    		return true;
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.zeroEncoders();
    	System.out.println("Turn is ending");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.zeroEncoders();
    	System.out.println("Turn was interrupted");
    }
}
