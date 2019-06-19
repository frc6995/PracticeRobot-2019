package frc.robot;

import frc.robot.buttons.*;
import frc.robot.RobotMap;

public class OI {

  //input devices
  public final Xbox xbox = new Xbox(RobotMap.OI_XBOX);
  public final BBoard buttonBoard = new BBoard(RobotMap.OI_BUTTONBOARD);

  public OI() {

    //Button Board Commands
    buttonBoard.right_index_toggleOnPress(new Robot.m_CargoIntakeC());
    buttonBoard.right_index_toggleOnPress(new Robot.m_CargoJettisonC());

  }
  
}
