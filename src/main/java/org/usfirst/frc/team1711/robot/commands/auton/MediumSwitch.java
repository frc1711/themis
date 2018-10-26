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
    	
    	addSequential(new Wait(.2));
    	addSequential(new AutoDrive(20, 0.7, 3));
    	
    	if(side == 'R')		
    		addSequential(new Turn(70));
    	else if(side == 'L')		
    		addSequential(new Turn(-70));
    	
    	addSequential(new Wait(.1));
    	addSequential(new AutoDrive(38, 0.7, 2));
    	
    	if(side == 'R')		
    		addSequential(new Turn(-70));
    	else if(side == 'L')	
    		addSequential(new Turn(70));
    	
    	addSequential(new Wait(0.3));
    	addParallel(new LiftGoToSetPoint(RobotMap.SWITCH_LIFT + 1000));
    	addSequential(new AutoDrive(45, 0.6, 3));
    	addSequential(new Wait(0.1));
    	addSequential(new TimedExpel(1)); 
    }

	public boolean equals(Object object)
	{
		return object.getClass().equals(this.getClass());
	}
}
