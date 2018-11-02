package org.usfirst.frc.team1711.robot.commands.lift;

import org.usfirst.frc.team1711.robot.OI;
import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HoldLift extends Command {
	public HoldLift(){
		
	}
	
	protected void initialize(){
		Robot.lift.runLift(0.2185);
	}
	
	protected void execute(){
		Robot.lift.runLift(0.2185);
	}
	
	protected boolean isFinished(){
		return false;
	}
	
	protected void end(){
		Robot.lift.runLift(0);
	}
	
	protected void inturrupted(){
		Robot.lift.runLift(0);
	}
}
