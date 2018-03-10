
package org.usfirst.frc.team1711.robot;

import org.usfirst.frc.team1711.robot.commands.auton.AutoDrive;
import org.usfirst.frc.team1711.robot.commands.auton.DoNothingAuton;
import org.usfirst.frc.team1711.robot.commands.auton.DriveExpelAuto;
import org.usfirst.frc.team1711.robot.commands.auton.MediumSwitch;
import org.usfirst.frc.team1711.robot.commands.drive.RawJoystickDrive;
import org.usfirst.frc.team1711.robot.commands.lift.PowerWinch;
import org.usfirst.frc.team1711.robot.subsystems.DriveSystem;
import org.usfirst.frc.team1711.robot.subsystems.IntakeSystem;
import org.usfirst.frc.team1711.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	public static DigitalInput autoSwitch;
	public static AnalogInput autonPot;
	public static OI oi;
	
	boolean chooserEnabled;

	Command autonomousCommand;
	Command teleopDrive;
	Command liftControl;
	SendableChooser<Command> chooser;
	SendableChooser<String> side;

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
		teleopDrive = new RawJoystickDrive();
		liftControl = new PowerWinch();
		autonomousCommand = new AutoDrive(80, 0.25);
		chooserEnabled = false;
		chooser = new SendableChooser<>();
		side = new SendableChooser<>();
//		autoSwitch = new DigitalInput(RobotMap.autoSwitch);
		autonPot = new AnalogInput(RobotMap.autonPot);
		//autonomousCommand = new DriveExpelAuto();
		oi = new OI(); //this needs to be last or else we will get BIG ERROR PROBLEM
		
		if(chooserEnabled)
		{
			side.addDefault("Right", "right");
			side.addObject("Left", "left");
			
			chooser.addDefault("Drive", new AutoDrive(80, 0.25));
			chooser.addObject("Drive Expel", new DriveExpelAuto());
			chooser.addObject("Do nothing", new DoNothingAuton());
			chooser.addObject("Center switch", new MediumSwitch(RobotMap.SIDE.left));
			
			SmartDashboard.putData("Auto mode", chooser);
			SmartDashboard.putData("Side selector", side); 
		} 
		
		CameraServer.getInstance().startAutomaticCapture();
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
//		System.out.println("Auto s: " + autonPot.getAverageVoltage());
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
		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		char[] field = gameMessage.toCharArray();
		
		if(chooserEnabled)
		{
			autonomousCommand = chooser.getSelected();
			
			if(side.getSelected().equals("right")&& field[0] == 'L')
				autonomousCommand = new AutoDrive(75, 0.25);
			else if(side.getSelected().equals("left") && field[0] == 'R')
				autonomousCommand = new AutoDrive(75, 0.25);
			if(field[0] == 'R' && autonomousCommand.equals(new MediumSwitch(RobotMap.SIDE.left)))
				autonomousCommand = new MediumSwitch(RobotMap.SIDE.left);
			else if(field[0] == 'L'&& autonomousCommand.equals(new MediumSwitch(RobotMap.SIDE.left)))
				autonomousCommand = new MediumSwitch(RobotMap.SIDE.right); 
		}
		
		if(autonPot.getAverageVoltage() <= 2.2)
			autonomousCommand = new AutoDrive(75, 0.25);
		else
		{
			if(field[0] == 'R')
				autonomousCommand = new MediumSwitch(RobotMap.SIDE.left);
			else if(field[0] == 'L')
				autonomousCommand = new MediumSwitch(RobotMap.SIDE.right);
		}
		
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
		Scheduler.getInstance().run();
		driveSystem.printOutput(1);
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
