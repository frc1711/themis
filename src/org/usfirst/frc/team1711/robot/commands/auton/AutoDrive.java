package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command 
{
	double desiredDistanceInches;
	double desiredDistancePulses;
	double encoderPulseAverage;
	double gyroAngle;

    public AutoDrive(double distance) 
    {
        requires(Robot.driveSystem);
        this.desiredDistanceInches = distance;
        //convert inches into pulses
        desiredDistancePulses = desiredDistanceInches;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.zeroEncoders();
    	Robot.driveSystem.zeroGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	encoderPulseAverage = Robot.driveSystem.getAverageEncoderValue();
    	gyroAngle = Robot.driveSystem.getGyroAngle();
    	double gyroCorrection = -1 * gyroAngle/50;
    	//we need more math if we wanna do anything other than go forward
    	Robot.driveSystem.cartesianDrive(0.5, 0, gyroCorrection);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	//currently only supports forward motion
    	if(encoderPulseAverage < desiredDistancePulses)
    		return false;
    	else
    		return true;
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
