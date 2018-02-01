package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 *
 */
public class DriveSystem extends Subsystem 
{
	WPI_TalonSRX frontLeftDrive;
	WPI_TalonSRX frontRightDrive;
	WPI_TalonSRX rearLeftDrive;
	WPI_TalonSRX rearRightDrive;
	
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
    	frontLeftDrive = new WPI_TalonSRX(RobotMap.FRD);
    	frontRightDrive = new WPI_TalonSRX(RobotMap.FLD);
    	rearLeftDrive = new WPI_TalonSRX(RobotMap.RRD);
    	rearRightDrive = new WPI_TalonSRX(RobotMap.FRD);
    	
    	frontRightDrive.setInverted(true);
    	rearLeftDrive.setInverted(true);
    	
    	left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
    	right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);
    	
    	basicDrive = new DifferentialDrive(left, right);
    	mecanumDrive = new MecanumDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
    	
    	gyro = new ADXRS450_Gyro();
    	
    	this.type = type;
    }
    
    public void arcadeDriving()
    {
    	basicDrive.arcadeDrive(-1 * RobotMap.driveStick.getRawAxis(RobotMap.throttleAxis), RobotMap.driveStick.getRawAxis(RobotMap.rotationAxis));
    }
    
    public void cartesianDrive()
    {
    	mecanumDrive.driveCartesian(RobotMap.driveStick.getRawAxis(0),
    			RobotMap.driveStick.getRawAxis(4),
    			RobotMap.driveStick.getRawAxis(1));
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
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

