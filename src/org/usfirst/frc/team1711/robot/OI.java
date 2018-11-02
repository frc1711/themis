package org.usfirst.frc.team1711.robot;

//import org.usfirst.frc.team1711.robot.commands.intake.Drop;
import org.usfirst.frc.team1711.robot.commands.intake.Expel;
import org.usfirst.frc.team1711.robot.commands.intake.Intake;
//import org.usfirst.frc.team1711.robot.commands.lift.FeederLift;
import org.usfirst.frc.team1711.robot.commands.lift.HoldLift;
import org.usfirst.frc.team1711.robot.commands.lift.SetBrake;

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
	
	JoystickButton unBrake = new JoystickButton(auxStick, 4);
	JoystickButton winchBrakeButton = new JoystickButton(auxStick, 2);
	JoystickButton intakeButton = new JoystickButton(auxStick, 5);
	JoystickButton expelButton = new JoystickButton(auxStick, 6);
	JoystickButton holdButton = new JoystickButton(auxStick, 3);
	JoystickButton dropButton = new JoystickButton(auxStick, 1);
	JoystickButton feederButton = new JoystickButton(auxStick, 8);
	
	public OI()
	{
		winchBrakeButton.whenPressed(new SetBrake(170));
		unBrake.whenPressed(new SetBrake(60));
		
		intakeButton.whileHeld(new Intake());
		expelButton.whileHeld(new Expel());
		
//		dropButton.whileHeld(new Drop());
		
		holdButton.whileHeld(new HoldLift());
//		feederButton.whileHeld(new FeederLift());
	}
}
