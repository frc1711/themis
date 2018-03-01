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
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
