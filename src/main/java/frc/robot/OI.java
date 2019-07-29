package frc.robot;

import frc.robot.interfaces.XBox;
import frc.robot.interfaces.JStick;
import frc.robot.RobotMap;

public class OI {

  //input devices
  public final XBox xBox = new XBox(RobotMap.OI_XBOX);
  public final JStick jStick = new JStick(RobotMap.OI_JSTICK);

  public OI() {

  }
}
