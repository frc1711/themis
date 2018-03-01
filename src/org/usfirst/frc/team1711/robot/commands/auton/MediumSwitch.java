package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Lou
 */
public class MediumSwitch extends CommandGroup {

    public MediumSwitch() {
    	addParallel(new TimedLift(10, .5));
    	addParallel(new AutoDrive(10));
    	addSequential(new Turn(10));
    	addSequential(new AutoDrive(10));
    	addSequential(new Turn(10));
    	addSequential(new AutoDrive(10));
    	addSequential(new TimedExpel(10));
    }
}
