
package org.usfirst.frc.team1711.robot;

import org.usfirst.frc.team1711.robot.commands.GyroDriveAuton;
import org.usfirst.frc.team1711.robot.commands.PowerWinch;
import org.usfirst.frc.team1711.robot.commands.RawJoystickDrive;
import org.usfirst.frc.team1711.robot.subsystems.DriveSystem;
import org.usfirst.frc.team1711.robot.subsystems.IntakeSystem;
import org.usfirst.frc.team1711.robot.subsystems.Lift;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{

	public static RobotMap robotMap;
	public static DriveSystem driveSystem;
	public static Lift lift;
	public static IntakeSystem intake;
	public static OI oi;

	Command autonomousCommand;
	Command teleopDrive;
	Command liftControl;
	public static TalonSRX testMotor;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		robotMap = new RobotMap();
		robotMap.init();
		driveSystem = new DriveSystem(DriveSystem.DriveType.MECANUM);
		lift = new Lift();
		intake = new IntakeSystem();
		teleopDrive = new RawJoystickDrive();
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		liftControl = new PowerWinch();
		SmartDashboard.putData("Auto mode", chooser);
		
		oi = new OI(); //this needs to be last or else we will get BIG ERROR PROBLEM
	}

	/**
	 * 
	 */
	@Override
	public void disabledInit() 
	{
		liftControl.cancel();
	}

	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() 
	{
		//autonomousCommand = chooser.getSelected();
		
//		autonomousCommand = new GyroDriveAuton(90);

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() 
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		
 		teleopDrive.start();
		liftControl.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		//System.out.println("Gyro: " + Robot.driveSystem.gyro.getAngle());
//		System.out.println("Brake: " + Robot.lift.brakeSwitch.get());
//		System.out.println("Bottom: " + Robot.lift.getBottomLimitSwitch());
//		System.out.println("Top: " + Robot.lift.getTopLimitSwitch());
		System.out.println("Angle: " + RobotMap.driveStick.getDirectionRadians());
		System.out.println("X axis: " + RobotMap.driveStick.getX(GenericHID.Hand.kLeft));
		System.out.println("Y axis: " + RobotMap.driveStick.getY(GenericHID.Hand.kLeft));
		System.out.println("Magnitude: " + RobotMap.driveStick.getMagnitude());
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
	}
}
