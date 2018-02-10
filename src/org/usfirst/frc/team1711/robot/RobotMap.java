package org.usfirst.frc.team1711.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	//MOTORS
	//drive motors
	public static int FRD = 2;
	public static int FLD = 3;
	public static int RRD = 0;
	public static int RLD = 1;
	//other motors
	public static int liftMotor = 0;
	public static int otherLiftMotor = 1;
	public static int brakeMotor = 2;
	public static int rightIntake = 6;
	public static int leftIntake = 7; 
	
	//placeholder numbers
	public static int throttleAxis = 1;
	public static int rotationAxis = 0;
	public static int holoRotationAxis = 2; 
	
	//sensors
	//digital ports
	public static int topLiftSwitch = 0;
	public static int bottomLiftSwitch = 1;
	public static int brakeSwitch = 2;
	
	public static Joystick driveStick;
	public static Joystick auxStick;
	
	//constants
	public static final double XBOX_DEADZONE = 0.25;
	
	public void init()
	{
		driveStick = new Joystick(0);
		auxStick = new Joystick(1);
	}
}
