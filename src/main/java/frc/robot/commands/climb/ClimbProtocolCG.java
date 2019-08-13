package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climb.climbcg.ClimbDeployC;
import frc.robot.commands.climb.climbcg.ClimbMoveForwardCG;
import frc.robot.commands.climb.climbcg.ClimbRetractFrontC;
import frc.robot.commands.climb.climbcg.ClimbRetractRearC;

public class ClimbProtocolCG extends CommandGroup {
  /**
   * Climb Protocol
   */
  public ClimbProtocolCG() {
    //Deploys Wheels.
    addSequential(new ClimbDeployC());
    //Moves forward, if front limit switch is pressed, 
    //stops going forward keeps going with algorithim, 
    //otherwise, if timed out, cancels operation, 
    //and moves backwards a small amount (to account for 
    //the bumpers not trapping itself on top of the 
    //podium) then retracts wheels
    addSequential(new ClimbMoveForwardCG(4, true));
    //Retracts front.
    addSequential(new ClimbRetractFrontC());
    //Moves forward, if front limit switch is pressed 
    //or timed out, stop moving forward
    addSequential(new ClimbMoveForwardCG(4, false));
    //retracts rear wheels
    addSequential(new ClimbRetractRearC());
    //moves forward for two seconds, and stops
    addSequential(new ClimbMoveForwardCG(2, false));
  }
}
