package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Lou and Abigail
 */

//this currently actually does the scale
//fix it if you want the switch!!!!

public class LongSwitch extends CommandGroup 
{

	char side;
	
    public LongSwitch(char side) 
    {
    	this.side = side;
    	
    	addSequential(new AutoDrive(140, 0.5));
    	addSequential(new TimedLift(2, -0.55));
    	addSequential(new Turn(70));
    	addSequential(new AutoDrive(75, 0.35));
    	addSequential(new Turn(-70));
    	addSequential(new AutoDrive(5, 0.25));
    	addSequential(new TimedExpel(1));
    }
}
