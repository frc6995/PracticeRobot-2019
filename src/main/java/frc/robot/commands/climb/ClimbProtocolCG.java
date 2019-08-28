package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.ClimbDeployC;
import frc.robot.commands.climb.climbcg.ClimbMoveForwardCG;
import frc.robot.commands.climb.climbcg.ClimbRetractFrontC;
import frc.robot.commands.climb.climbcg.ClimbRetractRearC;

public class ClimbProtocolCG extends CommandGroup {
  /**
   * Climb Protocol
   * Moves forward, if front limit switch is pressed, 
   * stops going forward keeps going with algorithim, 
   * otherwise, if timed out, cancels operation, 
   * and moves backwards a small amount (to account for 
   * the bumpers not trapping itself on top of the 
   * podium) then retracts wheels
   */
  public ClimbProtocolCG() {
    addSequential(new ClimbDeployC());
    addSequential(new ClimbMoveForwardCG(4, true));
    addSequential(new ClimbRetractFrontC());
    addSequential(new ClimbMoveForwardCG(4, false));
    addSequential(new ClimbRetractRearC());
    addSequential(new ClimbMoveForwardCG(2, false));
  }
}
