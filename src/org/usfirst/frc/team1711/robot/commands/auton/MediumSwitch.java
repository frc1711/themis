package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Lou
 */
public class MediumSwitch extends CommandGroup {

	RobotMap.SIDE side;
	
    public MediumSwitch(RobotMap.SIDE side) {
    	this.side = side;
    	
    	addSequential(new AutoDrive(40, 0.25));
    	addSequential(new TimedTurn(2, side));
    	addSequential(new Wait(0.1));
    	addSequential(new AutoDrive(40, 0.35));
    	if(side.equals(RobotMap.SIDE.left))		
    		addSequential(new TimedTurn(2, RobotMap.SIDE.right));
    	else
    		addSequential(new TimedTurn(2, RobotMap.SIDE.left));
    	addSequential(new Wait(0.5));
    	addSequential(new AutoDrive(60, 0.35));
    	addSequential(new TimedLift(1.5, -0.55));
    	addSequential(new TimedExpel(1));
    }

	public boolean equals(Object object)
	{
		return object.getClass().equals(this.getClass());
	}
}
