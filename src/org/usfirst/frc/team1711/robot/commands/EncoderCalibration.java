package org.usfirst.frc.team1711.robot.commands;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderCalibration extends Command 
{
	double pulses;
	double gyroAngle;
	double averageEncoderPulses;
	
	boolean gyroCorrected;
	
    public EncoderCalibration(double pulses, boolean corrected) 
    {
        requires(Robot.driveSystem);
        this.pulses = pulses;
        this.gyroCorrected = corrected;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.driveSystem.stopRobot();
    	//Robot.driveSystem.zeroGyro();
    	Robot.driveSystem.zeroEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//gyroAngle = Robot.driveSystem.getGyroAngle();
    	averageEncoderPulses = Robot.driveSystem.getAverageEncoderValue();
    	
    	double correctedRotation = 0;//-1 * gyroAngle/50;
    	
    	if(gyroCorrected)
    		Robot.driveSystem.cartesianDrive(0.5, 0, correctedRotation);
    	else
    		Robot.driveSystem.cartesianDrive(0.5, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(averageEncoderPulses < pulses)
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
