package org.usfirst.frc.team1711.robot;

import org.usfirst.frc.team1711.robot.commands.EncoderCalibration;
import org.usfirst.frc.team1711.robot.commands.LiftEncoderCalibration;
import org.usfirst.frc.team1711.robot.commands.intake.Expel;
import org.usfirst.frc.team1711.robot.commands.intake.Intake;
import org.usfirst.frc.team1711.robot.commands.lift.BrakeWinch;
import org.usfirst.frc.team1711.robot.commands.lift.PowerWinch;
import org.usfirst.frc.team1711.robot.commands.lift.QuickLift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	public static Joystick driveStick = new Joystick(RobotMap.driveStick);
	public static Joystick auxStick = new Joystick(RobotMap.auxStick);
	
	JoystickButton orthoButton = new JoystickButton(driveStick, 1);
	JoystickButton winchBrakeButton = new JoystickButton(auxStick, 2);
	JoystickButton calibrateEncoderButton = new JoystickButton(driveStick, 3);
	JoystickButton calibrateLiftEncoder = new JoystickButton(driveStick, 4);
	//JoystickButton runLift = new JoystickButton(driveStick, 4);
	//JoystickButton runLiftDown = new JoystickButton(driveStick, 3);
	
	JoystickButton intakeButton = new JoystickButton(auxStick, 5);
	JoystickButton expelButton = new JoystickButton(auxStick, 6);
	
	
	public OI()
	{
//		winchBrakeButton.whenPressed(new BrakeWinch());
		
		calibrateEncoderButton.whenPressed(new EncoderCalibration(100, false));
		calibrateLiftEncoder.whenPressed(new LiftEncoderCalibration(10));
		//runLift.whileHeld(new QuickLift(0.5));
		//runLiftDown.whileHeld(new QuickLift(-0.5));
		intakeButton.whileHeld(new Intake());
		expelButton.whileHeld(new Expel());
	}
}
