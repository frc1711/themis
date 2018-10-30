package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.Wait;
//import org.usfirst.frc.team1711.robot.commands.drive.GyroCorrectedDrive;
import org.usfirst.frc.team1711.robot.subsystems.Lift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */

//all our scale methods currently assume we start on the left
//you can edit this by passing in a side but we need to add another physical switch if we decide to add that functionality
public class ShortScale extends CommandGroup 
{
	char side;
    public ShortScale(char side) 
    {
    	this.side = side; 
  //  	addSequential(new Turn(-2));
 //   	addSequential(new DriveBiasCorrected(220, .5, 7));
    //	addSequential(new AutoDrive(200, .5, 7));
    	addSequential(new LiftGoToSetPoint(5000));
    	addSequential(new Turn(-60));
 //   	addSequential(new Wait(0.1));
 //   	addSequential(new AutoDrive(2, .25));
    	addSequential(new LiftGoToSetPoint(45000));
    	addSequential(new AutoDrive(6, .25, 1));
    	addSequential(new LiftGoToSetPoint(46000));
//    	addSequential(new LiftGoToSetPoint(46000));
//    	addSequential(new Wait(0.05));
    	addParallel(new TimedLift(3, 0.5));
    	addSequential(new TimedExpel(2));
 //   	addSequential(new TimedExpel(1)); 
    	addSequential(new AutoDrive(-10, .25, 2));
    }
}