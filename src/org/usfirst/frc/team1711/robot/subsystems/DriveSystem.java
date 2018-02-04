package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.RawJoystickDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 *
 */
public class DriveSystem extends Subsystem 
{
	public WPI_TalonSRX frontLeftDrive;
	public WPI_TalonSRX frontRightDrive;
	public WPI_TalonSRX rearLeftDrive;
	public WPI_TalonSRX rearRightDrive;
	
	public ADXRS450_Gyro gyro;
	
	boolean loadProfilingEnabled = false;
	double[] loadProfile = {1, 1, 1, 1};
	double[] rawAxleLoads = {1, 1, 1, 1};
	
	boolean secretMode = true;
	
	DifferentialDrive basicDrive;
	MecanumDrive mecanumDrive;
	
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	
	public enum DriveType {
		MECANUM, DIFFERENTIAL
	}
	
	public DriveType type;
	
    public DriveSystem(DriveType type)
    {
    	frontLeftDrive = new WPI_TalonSRX(RobotMap.FLD);
    	frontRightDrive = new WPI_TalonSRX(RobotMap.FRD);
    	rearLeftDrive = new WPI_TalonSRX(RobotMap.RLD);
    	rearRightDrive = new WPI_TalonSRX(RobotMap.RRD);
    	
//    	left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
//    	right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);
    	
//    	basicDrive = new DifferentialDrive(left, right);
    	mecanumDrive = new MecanumDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
    	
    	gyro = new ADXRS450_Gyro();
    	
    	this.type = type;
    }
    
    public void arcadeDriving()
    {
    	basicDrive.arcadeDrive(RobotMap.driveStick.getY(GenericHID.Hand.kLeft), RobotMap.driveStick.getX(GenericHID.Hand.kLeft));
    }
    
    public void cartesianDrive()
    {
    	if(Math.abs(RobotMap.driveStick.getX(GenericHID.Hand.kLeft)) > 0.15 ||
    			Math.abs(RobotMap.driveStick.getY(GenericHID.Hand.kLeft)) > 0.15 ||
    			Math.abs(RobotMap.driveStick.getY(GenericHID.Hand.kRight)) > 0.15)
    	{
    		mecanumDrive.driveCartesian(-1 * RobotMap.driveStick.getX(GenericHID.Hand.kLeft),
        			-1 * RobotMap.driveStick.getY(GenericHID.Hand.kLeft),
        			RobotMap.driveStick.getX(GenericHID.Hand.kRight));
    	}
    }
    
    public void polarDrive(double angle, double magnitude, double rotation)
    {
    	double largestValue = 0;
    	
    	if(angle < 0)
    		angle = ((2*Math.PI) + angle);
    	
    	//this code is based on this pdf:
    	//http://thinktank.wpi.edu/resources/346/ControllingMecanumDrive.pdf
    	//and this repository by Jack Smith:
    	//https://bitbucket.org/jackdeansmith/raptors-2015/
    	//please consult these sources before making changes to the following code
    	
    	double frontLeft = magnitude * (Math.sin(angle + Math.PI/4)) + rotation;
    	largestValue = Math.abs(frontLeft); //used to normalize the motor outputs to fit in the range [-1, 1]
    	
    	double frontRight = magnitude * (Math.sin(angle + Math.PI/4)) - rotation;
    	if(Math.abs(frontRight) > largestValue)
    		largestValue = Math.abs(frontRight);
    	
    	double rearLeft = magnitude * (Math.sin(angle + Math.PI/4)) + rotation;
    	if(Math.abs(rearLeft) > largestValue)
    		largestValue = Math.abs(rearLeft);
    	
    	double rearRight = magnitude * (Math.sin(angle + Math.PI/4)) - rotation;
    	if(Math.abs(rearRight) > largestValue)
    		largestValue = Math.abs(rearRight);
    	
    	//normalize the values to fit within the output range
    	if(largestValue > 1)
    	{
    		frontLeft /= largestValue;
    		frontRight /= largestValue;
    		rearLeft /= largestValue;
    		rearRight /= largestValue;
    		//alex wasn't here
    	}
    	else if(secretMode)
    	{
    		double scale = Math.abs(rotation) + magnitude;
    		if(scale > 1) {scale = 1;}
    		
    		frontLeft /= largestValue;
    		frontRight /= largestValue;
    		rearLeft /= largestValue;
    		rearRight /= largestValue;
    		
    		frontLeft *= scale;
    		frontRight *= scale;
    		rearLeft *= scale;
    		rearRight *= scale;
    	}
    	
    	setMotorOutputs(frontRight, frontLeft, rearRight, rearLeft);
    	
    }
    
    public void setMotorOutputs(double frontRight, double frontLeft, double rearRight, double rearLeft)
    {
    	frontLeftDrive.set(ControlMode.PercentOutput, frontLeft);
    	frontRightDrive.set(ControlMode.PercentOutput, frontRight);
    	rearLeftDrive.set(ControlMode.PercentOutput, rearLeft);
    	rearRightDrive.set(ControlMode.PercentOutput, rearRight);
    }
    
    public void enableLoadProfiling()
    {
    	loadProfilingEnabled = true;
    }
    
    public void disableLoadProfiling()
    {
    	loadProfilingEnabled = false;
    }
    
    public void setLoadProfile(double frontLeft, double frontRight, double rearLeft, double rearRight)
    {
    	rawAxleLoads[0] = frontLeft;
    	rawAxleLoads[1] = frontRight;
    	rawAxleLoads[2] = rearLeft;
    	rawAxleLoads[3] = rearRight;
    	
    	//find the smallest load
    	//this math is based on this repository:
    	//https://bitbucket.org/jackdeansmith/raptors-2015/
    	//please don't edit the following code without reading that first!
    	double smallestLoad = frontLeft;
    	if(frontRight < smallestLoad){smallestLoad = frontLeft;}
    	if(rearLeft < smallestLoad){smallestLoad = rearLeft;}
    	if(rearRight < smallestLoad){smallestLoad = rearRight;}
    	
    	//scale the loads
    	frontLeft = smallestLoad/frontLeft;
    	frontRight = smallestLoad/frontRight;
    	rearLeft = smallestLoad/rearLeft;
    	rearRight = smallestLoad/rearRight;
    	
    	//make sure the values are legit (i.e. positive)
    	if(frontLeft >= 0 && frontRight >= 0 && rearLeft >= 0 && rearRight >= 0)
    	{
    		//set the final profiles
    		loadProfile[0] = frontLeft;
        	loadProfile[1] = frontRight;
        	loadProfile[2] = rearLeft;
        	loadProfile[3] = rearRight;
    	}
    	else
    	{
    		System.out.println("Some loads are negative, please recheck your values");
    	}
    }
    
    public void mecanumDriving(double magnitude, double angle, double rotation)
    {
    	mecanumDrive.drivePolar(magnitude, angle, rotation);
    }
    
    public void orthoDriving(double direction)
    {
    	mecanumDrive.driveCartesian(0,RobotMap.driveStick.getRawAxis(RobotMap.rotationAxis), 0);
    }
    
    public void stopRobot()
    {
    	frontLeftDrive.set(ControlMode.PercentOutput, 0);
    	frontRightDrive.set(ControlMode.PercentOutput, 0);
    	rearLeftDrive.set(ControlMode.PercentOutput, 0);
    	rearRightDrive.set(ControlMode.PercentOutput, 0); 
    }
    
    public void turn(int angle, double speed)
    {
    	//need to know pos/neg right/left
    	if(angle > 0)
    		turnRight(speed);
    	else if(angle < 0)
    		turnLeft(speed);
    	else
    		stopRobot();
    }
    
    private void turnRight(double speed)
    {
    	frontLeftDrive.set(ControlMode.PercentOutput, speed);
    	frontRightDrive.set(ControlMode.PercentOutput, speed);
    	rearLeftDrive.set(ControlMode.PercentOutput, -speed);
    	rearRightDrive.set(ControlMode.PercentOutput, -speed); 
    }
    
    public void turnLeft(double speed)
    {
    	frontLeftDrive.set(ControlMode.PercentOutput, -speed);
    	frontRightDrive.set(ControlMode.PercentOutput, -speed);
    	rearLeftDrive.set(ControlMode.PercentOutput, speed);
    	rearRightDrive.set(ControlMode.PercentOutput, speed); 
    }
    
    public void zeroGyro()
    {
    	gyro.reset();
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new RawJoystickDrive());
    }
}

