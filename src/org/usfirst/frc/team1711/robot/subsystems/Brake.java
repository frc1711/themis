package org.usfirst.frc.team1711.robot.subsystems;

import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Brake extends Subsystem 
{
	Servo brakeServo;
	
	public Brake()
	{
		brakeServo = new Servo(RobotMap.brake);
	}
	
	public void setServo(double angle)
	{
		brakeServo.setAngle(angle);
	}
	
	public void chill()
	{
		brakeServo.setDisabled();
	}
	
	public double getServoAngle()
	{
		return brakeServo.getAngle();
	}

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

