package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveExpelAuto extends CommandGroup {
	
	public DriveExpelAuto() {
    	addSequential(new AutoDrive(75, 0.5));
    	addSequential(new TimedLift(2, -0.55));
    	addSequential(new TimedExpel(1));
    	//addSequential(new TimedLift(1, -0.25));
    	//addSequential(new TimedIntake(1));
    }
	
	public boolean equals(Object object)
	{
		return object.getClass().equals(this.getClass());
	}
}
