package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.ClimbDeployC;
import frc.robot.commands.climb.climbcg.ClimbMoveForwardCG;
import frc.robot.commands.climb.climbcg.ClimbRetractFrontC;
import frc.robot.commands.climb.climbcg.ClimbRetractRearC;

  /**
   * Climb Protocol
   * 
   * 1. Robot deploys wheels.
   * 2. Robot moves forward for time, if timed out and limit 
   *    switch isn't pressed, cancels climb using ClimbBackwardC. 
   * 3. If limit switch is pressed, retract front wheels,
   *    as we are on the podium.
   * 4. Robot moves forward enough to move its center 
   *    of gravity on the podium.
   * 5. Retracts rear wheels.
   * 6. Robot moves forward until completly on podium
   */
public class ClimbProtocolCG extends CommandGroup {
  public ClimbProtocolCG() {
    addSequential(new ClimbDeployC());
    addSequential(new ClimbMoveForwardCG(4, true));
    addSequential(new ClimbRetractFrontC());
    addSequential(new ClimbMoveForwardCG(4, false));
    addSequential(new ClimbRetractRearC());
    addSequential(new ClimbMoveForwardCG(2, false));
  }
}
