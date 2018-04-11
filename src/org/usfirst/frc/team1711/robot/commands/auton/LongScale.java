package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.Wait;

/**
 * @author Lou and Abigail
 */

//this currently actually does the scale
//fix it if you want the switch!!!!

public class LongScale extends CommandGroup 
{

	char side;
	
    public LongScale(char side) 
    {
    	this.side = side;
    	
    	addSequential(new AutoDrive(157, 0.5, 6));
    	addParallel(new LiftGoToSetPoint(5000));
    	addSequential(new Turn(65));
    	addSequential(new Wait(0.05));
    	addSequential(new AutoDrive(160, 0.35, 6));
    	addSequential(new Turn(-70));
    	addSequential(new AutoDrive(35, 0.25, 2));
    	addSequential(new LiftGoToSetPoint(45000));
    	addSequential(new TimedExpel(1)); 
    }
}
