
package org.usfirst.frc.team1711.robot;

import org.usfirst.frc.team1711.robot.commands.auton.AutoDrive;
import org.usfirst.frc.team1711.robot.commands.auton.MediumSwitch;
import org.usfirst.frc.team1711.robot.commands.auton.OneAndAHalfCubes;
import org.usfirst.frc.team1711.robot.commands.auton.ShortScale;
import org.usfirst.frc.team1711.robot.commands.auton.TwoCubes;
import org.usfirst.frc.team1711.robot.commands.drive.RawJoystickDrive;
import org.usfirst.frc.team1711.robot.commands.lift.PowerWinch;
import org.usfirst.frc.team1711.robot.subsystems.Brake;
import org.usfirst.frc.team1711.robot.subsystems.DriveSystem;
import org.usfirst.frc.team1711.robot.subsystems.IntakeSystem;
import org.usfirst.frc.team1711.robot.subsystems.Lift;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;


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
	public static Brake brakeSystem;
	public static DigitalInput autoSwitch;
	public static AnalogInput autonPot;
	public static UsbCamera camera;
	public static OI oi;
	
	boolean chooserEnabled;
	boolean testMode = false;
	
	Command autonomousCommand;
	Command teleopDrive;
	Command liftControl;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		robotMap = new RobotMap();
		robotMap.init();
		driveSystem = new DriveSystem();
		lift = new Lift();
		intake = new IntakeSystem();
		brakeSystem = new Brake();
		teleopDrive = new RawJoystickDrive();
		liftControl = new PowerWinch();
		autonomousCommand = new AutoDrive(2, 1, 4);
		chooserEnabled = false;
//		autoSwitch = new DigitalInput(RobotMap.autoSwitch);
		autonPot = new AnalogInput(RobotMap.autonPot);
//		camera = new UsbCamera("usb-camera", 1);
		//autonomousCommand = new DriveExpelAuto();
		oi = new OI(); //this needs to be last or else we will get BIG ERROR PROBLEM
		 
		//let's see if this does it
//		camera.setExposureManual(50); //these lines are a maybe
//		camera.setExposureHoldCurrent();
/*		camera.setFPS(30);
		camera.setResolution(320, 240); */
/*		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture();
		cam0.setExposureManual(50);
		cam0.setResolution(320, 240);
		cam0.setFPS(30); */
	}

	/**
	 * 
	 */
	@Override
	public void disabledInit() 
	{
		
	}

	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	//	System.out.println(autonPot.getAverageVoltage());
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
		driveSystem.setSafety(false);
		lift.zeroLiftEncoder();
		
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		
		//TEST NEW AUTO DRIVE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		if(!gameMessage.equals("")) //if it's not empty
		{
			char[] field = gameMessage.toCharArray(); //get the field layout. also, it goes xyz. X = scale, Y = switch, Z = enemy scale
			
			if(autonPot.getAverageVoltage() <= 1.51) //if the voltage is less than or equal to 1.51, do: 
			{
				autonomousCommand = new AutoDrive(100, .5, 6); //drive forward
				System.out.println("Drive forward: potentiometer chose " + autonPot.getAverageVoltage());
			}
			else 
			{
				autonomousCommand = new MediumSwitch(field[0]); //create a new MediumSwitch auton, getting the FIRST element of the field 
				System.out.println("Center switch: potentiometer chose " + autonPot.getAverageVoltage());
			}
			
			testMode = false;
			
			if(testMode)
			{
				if(field[1] == 'R')
					autonomousCommand = new ShortScale(field[1]);
			}
			//TAKE ME OUT
//			autonomousCommand = new ShortScale(field[1]);
//			autonomousCommand = new MediumSwitch(field[0]);
		}
		else //vroom vroom 
		{
			autonomousCommand = new AutoDrive(75, 0.25, 4);
			System.out.println("Auto data not received in auto init, driving forward");
		}
		// schedule the autonomous command (example)
		brakeSystem.setServo(70);
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
		
		driveSystem.setSafety(true);
		
 		teleopDrive.start();
		liftControl.start();
		brakeSystem.setServo(70);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
//		driveSystem.printOutput(1);
//		System.out.println(driveSystem.getGyroAngle());
//		lift.printOutput(1);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
	}
}
