package frc.robot.commands.climb.climbcg;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.climb.climbcg.moveforwardcg.MoveClimbBackwardC;
import frc.robot.commands.climb.climbcg.moveforwardcg.MoveClimbForwardC;
import frc.robot.commands.climb.climbcg.moveforwardcg.MoveClimbRetractC;

public class ClimbMoveForwardCG extends CommandGroup {
  /**
   * Move Forward Algorithm 
   * 
   * 1. Robot moves forward for desired time.
   * 2. If limit switch on front wheels is pressed, stop.
   * 3. Or, if timed out, and no limit switch was pressed,
   *    move backwards for small amount of time, and retract
   *    wheels, ready to try again!
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
