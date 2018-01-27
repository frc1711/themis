package org.usfirst.frc.team1711.robot;

import edu.wpi.first.wpilibj.Joystick;

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
	public static int FRD = 3;
	public static int FLD = 1;
	public static int RRD = 2;
	public static int RLD = 0;
	//other motors
	public static int liftMotor = 4;
	public static int brakeMotor = 5;
	
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
	
	public void init()
	{
		driveStick = new Joystick(0);
		auxStick = new Joystick(1);
	}
}
