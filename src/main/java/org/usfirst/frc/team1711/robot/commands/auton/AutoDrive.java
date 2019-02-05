package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
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
	double speed;
	double startTime;
	double currentTime;
	double timeout;

    public AutoDrive(double distance, double speed, double timeout) 
    {
        requires(Robot.driveSystem);
        this.desiredDistanceInches = distance;
        
        //convert inches into pulses
        desiredDistancePulses = desiredDistanceInches * 262;
        this.speed = speed;
        
        //the timeout allows us to move on if we are squaring up with a hard surface (such as the switch) and the wheels are not
        //able to move freely and increase encoder counts, but we are in position to advance in the command group
        this.timeout = timeout * 1000; //timeout param is seconds, system deals in millis
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.zeroEncoders();
    	Robot.driveSystem.frontLeftDrive.setInverted(true);
    	Robot.driveSystem.rearLeftDrive.setInverted(true);
    	Robot.driveSystem.zeroGyro();
    	
    	if(desiredDistanceInches > 0)
    		speed *= -1;
    	
    	startTime = System.currentTimeMillis();
    	
    	System.out.println("AutoDrive is being initialized");
    	System.out.println("The current encoder count is: " + Robot.driveSystem.getAverageEncoderValue());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	currentTime = System.currentTimeMillis();
    	encoderPulseAverage = Robot.driveSystem.getAverageEncoderValue();
//    	gyroAngle = Robot.driveSystem.getGyroAngle();
//    	double gyroCorrection = -1 * gyroAngle/50;
    	if(desiredDistancePulses - encoderPulseAverage < 500)
    		Robot.driveSystem.driveStatic(0.5 * speed);
    	else
    		Robot.driveSystem.driveStatic(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(currentTime - startTime > timeout)
    		return true;
    	//currently only supports forward motion
    	if(desiredDistancePulses > 0 && encoderPulseAverage < desiredDistancePulses)
    		return false;
    	else if(desiredDistancePulses < 0 && encoderPulseAverage > desiredDistancePulses)
    		return false;
    	else
    		return true;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.frontLeftDrive.setInverted(false);
    	Robot.driveSystem.rearLeftDrive.setInverted(false);
    	System.out.println("AutoDrive is ending");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.frontLeftDrive.setInverted(false);
    	Robot.driveSystem.rearLeftDrive.setInverted(false);
    	System.out.println("AutoDrive was interrupted");
    }
}
