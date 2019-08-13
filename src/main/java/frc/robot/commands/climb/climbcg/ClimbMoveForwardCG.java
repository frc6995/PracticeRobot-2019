package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.climb.climbcg.moveforwardcg.MoveClimbBackwardC;
import frc.robot.commands.climb.climbcg.moveforwardcg.MoveClimbForwardC;
import frc.robot.commands.climb.climbcg.moveforwardcg.MoveClimbRetractC;

public class ClimbMoveForwardCG extends CommandGroup {
  /**
   * move Forward algorithm, modifiers: 
   * 1. time, desired time to run as a timout
   * 2. useCancel, if you want to use the move backwards and retract algorithm,
   * use "true" if to just stop the wheels, use "false" 
   */
  public ClimbMoveForwardCG(int time, boolean useCancel) {

    //move forward while checking if limit switch is being pressed
    addParallel(new MoveClimbForwardC(time));
    //if a limit switch is pressed, stop
    if (Robot.m_ClimbS.limitSwitchPressed() == true) {
      Robot.m_ClimbS.legWheels(0.0);
    //if timed out, 
    } else if (isFinished()) {
      //move backwards and retract wheels
      if (useCancel == true) {
        addSequential(new MoveClimbBackwardC());
        addSequential(new MoveClimbRetractC());
      }
      //stop
      if (useCancel == false) {
        Robot.m_ClimbS.legWheels(0.0);
      }
    }
  }
}
