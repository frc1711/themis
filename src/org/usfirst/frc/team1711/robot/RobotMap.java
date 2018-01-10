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
	public static int FRD = 0;
	public static int FLD = 1;
	public static int RRD = 2;
	public static int RLD = 3;
	
	//placeholder numbers
	public static int throttleAxis = 0;
	public static int rotationAxis = 1;
	
	public static Joystick driveStick;
	
	public void init()
	{
		driveStick = new Joystick(0);
	}
}
