package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShortSwitch extends CommandGroup {
	
	char side; 
	
    public ShortSwitch(char side) {
    	this.side = side; 
    	
    	addSequential(new AutoDrive(80, 0.5, 4));
    	if (side == 'L')
    		addSequential(new Turn(-90));
    	else if (side == 'R')
    		addSequential(new Turn(90));
    	addSequential(new TimedLift(2, -0.55));
    	addSequential(new TimedExpel(1));
    }
}
