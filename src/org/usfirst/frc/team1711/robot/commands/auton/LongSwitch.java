package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Lou and Abigail
 */
public class LongSwitch extends CommandGroup {

	RobotMap.SIDE side;
	
    public LongSwitch(RobotMap.SIDE side) {
    	this.side = side;
    	
    	addSequential(new AutoDrive(105, 0.5));
    	addSequential(new TimedLift(2, -0.55));
    	addSequential(new TimedTurn(1, side));
    	addSequential(new AutoDrive(75, 0.35));
    	addSequential(new TimedTurn(1, side));
    	addSequential(new AutoDrive(5, 0.25));
    	addSequential(new TimedExpel(1));
    }
}
