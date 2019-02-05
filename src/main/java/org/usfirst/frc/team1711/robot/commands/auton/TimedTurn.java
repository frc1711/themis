package org.usfirst.frc.team1711.robot.commands.auton;

import org.usfirst.frc.team1711.robot.Robot;
import org.usfirst.frc.team1711.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedTurn extends TimedCommand {
	
	RobotMap.SIDE side;

    public TimedTurn(double timeout, RobotMap.SIDE side) {
        super(timeout);
        requires(Robot.driveSystem);  
        this.side = side;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSystem.stopRobot();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(this.side.equals(RobotMap.SIDE.left))
    		Robot.driveSystem.turnLeft(0.3);
    	else if(this.side.equals(RobotMap.SIDE.right))
    		Robot.driveSystem.turnRight(0.3);
    }

    // Called once after timeout
    protected void end() {
    	Robot.driveSystem.stopRobot();
    	Robot.driveSystem.zeroEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSystem.stopRobot();
    }
}
