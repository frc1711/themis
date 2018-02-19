package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.drive.OrthoSwitchDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSystem extends Subsystem implements MotorSafety
{
	public WPI_TalonSRX frontLeftDrive;
	public WPI_TalonSRX frontRightDrive;
	public WPI_TalonSRX rearLeftDrive;
	public WPI_TalonSRX rearRightDrive;
	
	public MotorSafetyHelper safety;
	
	public AHRS gyro;
	
	boolean loadProfilingEnabled = false;
	double[] loadProfile = {1, 1, 1, 1};
	double[] rawAxleLoads = {1, 1, 1, 1};
	
	boolean secretMode = false;
	
	MecanumDrive mecanumDrive;
	
    public DriveSystem()
    {
    	frontLeftDrive = new WPI_TalonSRX(RobotMap.FLD);
    	frontRightDrive = new WPI_TalonSRX(RobotMap.FRD);
    	rearLeftDrive = new WPI_TalonSRX(RobotMap.RLD);
    	rearRightDrive = new WPI_TalonSRX(RobotMap.RRD);
    	
    	safety = new MotorSafetyHelper(this);
    	
//    	frontLeftDrive.setInverted(false);
//    	frontRightDrive.setInverted(true);
//		rearRightDrive.setInverted(true);
    	
    	mecanumDrive = new MecanumDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
    	
    	gyro = new AHRS(SerialPort.Port.kUSB);
    }
    
    public void cartesianDrive(double y, double x, double rotation)
    {
    	mecanumDrive.driveCartesian(y, x, rotation);
    }
    
    public void driveCartesian(double ySpeed, double xSpeed, double zRotation) 
    {
        double[] wheelSpeeds = new double[4];
        wheelSpeeds[0] = xSpeed + ySpeed + zRotation;
        wheelSpeeds[1] = xSpeed - ySpeed + zRotation;
        wheelSpeeds[2] = -xSpeed + ySpeed + zRotation;
        wheelSpeeds[3] = -xSpeed - ySpeed + zRotation;

        wheelSpeeds = normalize(wheelSpeeds);

        setMotorOutputs(wheelSpeeds[0], wheelSpeeds[1], wheelSpeeds[2], wheelSpeeds[3]);
    }
    
    private double[] normalize(double[] speeds)
    {
    	double largestValue = 0;
    	
    	if(Math.abs(speeds[0]) > largestValue) { largestValue = Math.abs(speeds[0]);}
    	if(Math.abs(speeds[1]) > largestValue) { largestValue = Math.abs(speeds[1]);}
    	if(Math.abs(speeds[2]) > largestValue) { largestValue = Math.abs(speeds[2]);}
    	if(Math.abs(speeds[3]) > largestValue) { largestValue = Math.abs(speeds[3]);}
    	
    	if(largestValue > 1)
     	{
     		speeds[0] /= largestValue;
     		speeds[1] /= largestValue;
     		speeds[2] /= largestValue;
     		speeds[3] /= largestValue;
     		//alex wasn't here
     	}
    	
    	return speeds;
    }
    
    public void polarDrive(double angle, double magnitude, double rotation)
    {
     	double largestValue = 0;
     	
     	if(angle < 0)
     		angle += 2*Math.PI;
     	
     	//this code is based on this pdf:
     	//http://thinktank.wpi.edu/resources/346/ControllingMecanumDrive.pdf
     	//and this repository by Jack Smith:
     	//https://bitbucket.org/jackdeansmith/raptors-2015/
     	//please consult these sources before making changes to the following code
     	
     	double frontLeft = magnitude * (Math.sin(angle + Math.PI/4)) + rotation;
     	largestValue = Math.abs(frontLeft); //used to normalize the motor outputs to fit in the range [-1, 1]
     	//System.out.println("FL: " + frontLeft);
     	
     	double frontRight = magnitude * (Math.sin(angle + Math.PI/4)) - rotation;
     	//System.out.println("FR : " + frontRight);
     	if(Math.abs(frontRight) > largestValue)
     		largestValue = Math.abs(frontRight);
     	
     	double rearLeft = magnitude * (Math.sin(angle + Math.PI/4)) + rotation;
     	//System.out.println("RL: " + rearLeft);
     	if(Math.abs(rearLeft) > largestValue)
     		largestValue = Math.abs(rearLeft);
     	
     	double rearRight = magnitude * (Math.sin(angle + Math.PI/4)) - rotation;
     	//System.out.println("RR: " + rearRight);
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
     		
     	}
     	
     	setMotorOutputs(frontRight, frontLeft, rearRight, rearLeft);
     	
     }
     
     public void setMotorOutputs(double frontRight, double frontLeft, double rearRight, double rearLeft)
     {
     	frontLeftDrive.set(frontLeft);
     	frontRightDrive.set(frontRight);
     	rearLeftDrive.set(rearLeft);
     	rearRightDrive.set(rearRight);
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
    
    public void stopRobot()
    {
    	frontLeftDrive.set(0);
    	frontRightDrive.set(0);
    	rearLeftDrive.set(0);
    	rearRightDrive.set(0); 
    }
    
    public void turn(double angle, double speed)
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
    	frontLeftDrive.set(-speed);
    	frontRightDrive.set(speed);
    	rearLeftDrive.set(-speed);
    	rearRightDrive.set(-speed); 
    }
    
    public void turnLeft(double speed)
    {
    	frontLeftDrive.set(-speed);
    	frontRightDrive.set(speed);
    	rearLeftDrive.set(-speed);
    	rearRightDrive.set(speed); 
    }
    
    public double getFrontLeftEncoder()
    {
    	return frontLeftDrive.getSensorCollection().getQuadraturePosition();
    }
    
    public double getFrontRightEncoder()
    {
    	return frontRightDrive.getSensorCollection().getQuadraturePosition();
    }
    
    public double getRearLeftEncoder()
    {
    	return rearLeftDrive.getSensorCollection().getQuadraturePosition();
    }
    
    public double getRearRightEncoder()
    {
    	return rearRightDrive.getSensorCollection().getQuadraturePosition();
    }
    
    public double getAverageEncoderValue()
    {
    	double frontLeft = getFrontLeftEncoder();
    	double frontRight = getFrontRightEncoder();
    	double rearLeft = getRearLeftEncoder();
    	double rearRight = getRearRightEncoder();
    	
    	double sum = frontLeft + frontRight + rearLeft + rearRight;
    	double average = sum / 4;
    	
    	return average;
    }
    
    public void zeroEncoders()
    {
    	//second number is timeout in ms
    	frontLeftDrive.getSensorCollection().setQuadraturePosition(0, 15);
    	frontRightDrive.getSensorCollection().setQuadraturePosition(0, 15);
    	rearLeftDrive.getSensorCollection().setQuadraturePosition(0, 15);
    	rearRightDrive.getSensorCollection().setQuadraturePosition(0, 15);
    }
    
    public double getGyroAngle()
    {
    	return gyro.getAngle();
    }
    
    public void zeroGyro()
    {
    	gyro.zeroYaw();
    }
    
    public boolean isGyroConnected()
    {
    	return gyro.isConnected();
    }
    
    public boolean isGyroCalibrating()
    {
    	return gyro.isCalibrating();
    }
    
    public void printOutput(int setting)
    {
    	switch(setting)
    	{
    	case 0:
    		//System.out.println("Average encoder: "   getAverageEncoderValue());
    		SmartDashboard.putNumber("Average drive encoder value", getAverageEncoderValue());
    	case 1:
    		//System.out.println("Front left "   getFrontLeftEncoder());
    		//System.out.println("Front right "   getFrontRightEncoder());
    		//System.out.println("Rear left "   getRearLeftEncoder());
    		//System.out.println("Rear right "   getRearRightEncoder());
    		SmartDashboard.putNumber("Front left", getFrontLeftEncoder());
    		SmartDashboard.putNumber("Front right", getFrontRightEncoder());
    		SmartDashboard.putNumber("Rear left", getRearLeftEncoder());
    		SmartDashboard.putNumber("Rear right", getRearRightEncoder());
    	}
    }
    
    public void setExpiration(double timeout) {};
    public double getExpiration() {};

    public void initDefaultCommand() 
    {
        setDefaultCommand(new OrthoSwitchDrive());
    }
}

