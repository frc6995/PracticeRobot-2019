package frc.robot;

import frc.robot.buttons.Xbox;
import frc.robot.buttons.BBoard;
import frc.robot.commands.climb.ClimbDeployC;
import frc.robot.commands.climb.MoveC;
import frc.robot.commands.climb.RetractFrontC;
import frc.robot.commands.climb.RetractRearC;
import frc.robot.RobotMap;

public class OI {

  //input devices
  public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  //public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

  public OI() {
    xbox.x_runOnPressed(new ClimbDeployC());
    xbox.y_runOnPressed(new RetractFrontC());
    xbox.b_runOnPressed(new RetractRearC());

    xbox.dpad_up_runOnPressed(new MoveC(1));
    xbox.dpad_down_runOnPressed(new MoveC(-1));
  }
}
