package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ClimbS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.RobotPreferences;

public class Robot extends TimedRobot {
  
  //Subsystems
  public static DrivebaseS m_drivebaseS;
  public static ClimbS m_ClimbS;

  public static OI m_oi;

  Preferences prefs;

  double dbrotThrottle;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    //Subsystems
    m_drivebaseS = new DrivebaseS();
    m_ClimbS = new ClimbS();
    //OI
    m_oi = new OI();

    prefs = Preferences.getInstance();
		prefs.putDouble("Drivebase Rotation Throttle 2", 1.0); 
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("TEST PREFS", RobotPreferences.rotThrottle.getValue());
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {

      m_autonomousCommand.start();

    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {

      m_autonomousCommand.cancel();

    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
