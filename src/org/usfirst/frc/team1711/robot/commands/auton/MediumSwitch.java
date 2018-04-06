package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;
import org.usfirst.frc.team1711.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Lou
 */
public class MediumSwitch extends CommandGroup {

	char side;
	
    public MediumSwitch(char side) 
    {
    	this.side = side;
    	System.out.println(side);
    	
    	addSequential(new Wait(.1));
    	addSequential(new AutoDrive(25, 0.5, 3));
    	
    	if(side == 'R')		
    		addSequential(new Turn(70));
    	else if(side == 'L')		
    		addSequential(new Turn(-70));
    	
    	addSequential(new Wait(.1));
    	addSequential(new AutoDrive(33, 0.5, 2));
    	
    	if(side == 'R')		
    		addSequential(new Turn(-70));
    	else if(side == 'L')	
    		addSequential(new Turn(70));
    	
    	addSequential(new Wait(0.3));
    	addParallel(new LiftGoToSetPoint(RobotMap.SWITCH_LIFT));
    	addSequential(new AutoDrive(37, 0.5, 2));
    	addSequential(new Wait(0.1));
    	addSequential(new TimedExpel(1)); 
    }

	public boolean equals(Object object)
	{
		return object.getClass().equals(this.getClass());
	}
}
