package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Lou
 */
public class ShortSwitchScale extends CommandGroup {
	
	char side; 
	
    public ShortSwitchScale(char side) {
    	this.side = side; 
    	System.out.println(side); 
    	
    	addSequential(new AutoDrive(75, 0.5, 4));
    	addSequential(new TimedLift(2, -0.55));
    	addSequential(new TimedExpel(1));
    	addSequential(new AutoDrive(-10, 0.5, 2));
    	
    	if (side == 'L')
    		addSequential(new Turn(90)); //not true value
    	else if (side == 'R')
    		addSequential(new Turn(-90)); //not true value 
    	addSequential(new AutoDrive(40, 0.5, 2));
    	
    	if (side == 'L')
    		addSequential(new Turn(-90));
    	else if (side == 'R')
    		addSequential(new Turn(90));
    	
    	addSequential(new AutoDrive(165, 0.5, 2));
    	
    	if (side == 'L')
    		addSequential(new Turn (-90));
    	else if (side == 'R')
        	addSequential(new Turn (90));
    	
    	addSequential(new AutoDrive(20, 0.5, 2));
    	addParallel(new TimedIntake(2));
    	addSequential(new AutoDrive(-10, 0.5, 2));

    	if (side == 'L')
    		addSequential(new Turn(-90));
    	else if (side == 'R')
    		addSequential(new Turn(90));
    	
    	addSequential(new AutoDrive(20, 0.5, 2)); //this is a complete guess
    	
    	if (side == 'L')
    		addSequential(new Turn(-90));
    	else if (side == 'R')
    		addSequential(new Turn(90));
    	
    	addSequential(new AutoDrive(20, 0.5, 2));
    	addSequential(new LiftGoToSetPoint(45000));
    	addSequential(new TimedExpel(2)); 
    }
}
