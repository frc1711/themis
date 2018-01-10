package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

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
	
    public DriveSystem()
    {
    	frontLeftDrive = new WPI_TalonSRX(RobotMap.FRD);
    	frontRightDrive = new WPI_TalonSRX(RobotMap.FLD);
    	rearLeftDrive = new WPI_TalonSRX(RobotMap.RRD);
    	rearRightDrive = new WPI_TalonSRX(RobotMap.FRD);
    }
    
    

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

