package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveExpelAuto extends CommandGroup {

    public DriveExpelAuto() {
    	addSequential(new AutoDrive(100));
    	addParallel(new TimedLift(1, 0.25));
    	addSequential(new TimedExpel(1));
    	addSequential(new TimedLift(1, -0.25));
    	addSequential(new TimedIntake(1));
    }
}
