package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.RawJoystickDrive;

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
    	
    	frontRightDrive.setInverted(true);
    	rearRightDrive.setInverted(true);
    	
    	left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
    	right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);
    	
    	basicDrive = new DifferentialDrive(left, right);
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
    	frontLeftDrive.set(0);
    	frontRightDrive.set(0);
    	rearLeftDrive.set(0);
    	rearRightDrive.set(0); 
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
    
    public void zeroGyro()
    {
    	gyro.reset();
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new RawJoystickDrive());
    }
}

