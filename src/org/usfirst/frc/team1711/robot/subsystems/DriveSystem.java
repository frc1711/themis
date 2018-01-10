package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	
	DifferentialDrive basicDrive;
	MecanumDrive mecanumDrive;
	
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	
    public DriveSystem()
    {
    	frontLeftDrive = new WPI_TalonSRX(RobotMap.FRD);
    	frontRightDrive = new WPI_TalonSRX(RobotMap.FLD);
    	rearLeftDrive = new WPI_TalonSRX(RobotMap.RRD);
    	rearRightDrive = new WPI_TalonSRX(RobotMap.FRD);
    	
    	left = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
    	right = new SpeedControllerGroup(frontRightDrive, rearRightDrive);
    	
    	basicDrive = new DifferentialDrive(left, right);
    	mecanumDrive = new MecanumDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
    }
    
    public void arcadeDriving()
    {
    	basicDrive.arcadeDrive(RobotMap.driveStick.getRawAxis(RobotMap.throttleAxis), RobotMap.driveStick.getRawAxis(RobotMap.rotationAxis));
    }
    
    public void mecanumDriving(double magnitude, double angle, double rotation)
    {
    	mecanumDrive.drivePolar(magnitude, angle, rotation);
    }

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

