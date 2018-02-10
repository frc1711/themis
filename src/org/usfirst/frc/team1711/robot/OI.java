package org.usfirst.frc.team1711.robot;

import org.usfirst.frc.team1711.robot.commands.drive.MotorTest;
import org.usfirst.frc.team1711.robot.commands.drive.OrthoLockDrive;
import org.usfirst.frc.team1711.robot.commands.intake.Expel;
import org.usfirst.frc.team1711.robot.commands.intake.Intake;
import org.usfirst.frc.team1711.robot.commands.lift.BrakeWinch;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	JoystickButton orthoButton = new JoystickButton(RobotMap.driveStick, 1);
	JoystickButton winchBrakeButton = new JoystickButton(RobotMap.auxStick, 2);
	JoystickButton motorTestButton = new JoystickButton(RobotMap.driveStick, 3);
	
	JoystickButton intakeButton = new JoystickButton(RobotMap.driveStick, 5);
	JoystickButton expelButton = new JoystickButton(RobotMap.driveStick, 6);
	
	
	public OI()
	{
		winchBrakeButton.whenPressed(new BrakeWinch());
		motorTestButton.whileHeld(new MotorTest(Robot.driveSystem.rearRightDrive));
		
		intakeButton.whileHeld(new Intake());
		expelButton.whileHeld(new Expel());
//		orthoButton.whileHeld(new OrthoLockDrive());
	}
}
