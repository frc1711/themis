package org.usfirst.frc.team1711.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShortSwitch extends CommandGroup {

    public ShortSwitch() {
    	addSequential(new AutoDrive(75, 0.5));
    	addSequential(new TimedLift(2, -0.55));
    	addSequential(new TimedExpel(1));
    }
}
