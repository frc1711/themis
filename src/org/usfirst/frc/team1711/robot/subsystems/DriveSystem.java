package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.drive.RawJoystickDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSystem extends Subsystem 
{
	public WPI_TalonSRX frontLeftDrive;
	public WPI_TalonSRX frontRightDrive;
	public WPI_TalonSRX rearLeftDrive;
	public WPI_TalonSRX rearRightDrive;
	
	public AHRS gyro;
	
	boolean secretMode = false;
	
	MecanumDrive mecanumDrive;
	
    public DriveSystem()
    {
    	frontLeftDrive = new WPI_TalonSRX(RobotMap.FLD);
    	frontRightDrive = new WPI_TalonSRX(RobotMap.FRD);
    	rearLeftDrive = new WPI_TalonSRX(RobotMap.RLD);
    	rearRightDrive = new WPI_TalonSRX(RobotMap.RRD);
    	
    	frontRightDrive.setInverted(true);
    	rearRightDrive.setInverted(true);
    	
    	mecanumDrive = new MecanumDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
    	
    	gyro = new AHRS(SerialPort.Port.kUSB);
    }
    
    public void cartesianDrive(double y, double x, double rotation)
    {
    	mecanumDrive.driveCartesian(y, x, rotation);
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
    	frontLeftDrive.set(speed);
    	frontRightDrive.set(speed);
    	rearLeftDrive.set(-speed);
    	rearRightDrive.set(-speed); 
    }
    
    public void turnLeft(double speed)
    {
    	frontLeftDrive.set(-speed);
    	frontRightDrive.set(-speed);
    	rearLeftDrive.set(speed);
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
    
    public void printOutput(int setting)
    {
    	switch(setting)
    	{
    	case 0:
    		//System.out.println("Average encoder: " + getAverageEncoderValue());
    		SmartDashboard.putNumber("Average drive encoder value", getAverageEncoderValue());
    	case 1:
    		//System.out.println("Front left " + getFrontLeftEncoder());
    		//System.out.println("Front right " + getFrontRightEncoder());
    		//System.out.println("Rear left " + getRearLeftEncoder());
    		//System.out.println("Rear right " + getRearRightEncoder());
    		SmartDashboard.putNumber("Front left", getFrontLeftEncoder());
    		SmartDashboard.putNumber("Front right", getFrontRightEncoder());
    		SmartDashboard.putNumber("Rear left", getRearLeftEncoder());
    		SmartDashboard.putNumber("Rear right", getRearRightEncoder());
    	}
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new RawJoystickDrive());
    }
}

