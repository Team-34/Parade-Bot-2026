package frc.robot;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

public class DriveTrain extends SubsystemBase {
  // 1. Initialize TalonFX motors with their CAN IDs
  private final TalonFX m_leftLeader = new TalonFX(1);
  private final TalonFX m_leftFollower = new TalonFX(2);
  private final TalonFX m_rightLeader = new TalonFX(3);
  private final TalonFX m_rightFollower = new TalonFX(4);

  // 2. Phoenix 6 control requests
  private final DutyCycleOut m_leftRequest = new DutyCycleOut(0);
  private final DutyCycleOut m_rightRequest = new DutyCycleOut(0);

  public DriveTrain() {
    // 3. Configure Left Side (Standard Counter-Clockwise Positive + Brake Mode)
    var leftConfig = new MotorOutputConfigs();
    leftConfig.Inverted = InvertedValue.CounterClockwise_Positive;
    leftConfig.NeutralMode = NeutralModeValue.Brake;
    m_leftLeader.getConfigurator().apply(leftConfig);

    // 4. Configure Right Side (Inverted Clockwise Positive + Brake Mode)
    var rightConfig = new MotorOutputConfigs();
    rightConfig.Inverted = InvertedValue.Clockwise_Positive;
    rightConfig.NeutralMode = NeutralModeValue.Brake;
    m_rightLeader.getConfigurator().apply(rightConfig);

    // 5. Set followers to match their leaders
    m_leftFollower.setControl(new Follower(m_leftLeader.getDeviceID(), MotorAlignmentValue.Aligned));
    m_rightFollower.setControl(new Follower(m_rightLeader.getDeviceID(), MotorAlignmentValue.Aligned));
  }

  /**
   * Sets the speed of the left and right sides of the drivetrain.
   * @param leftSpeed Speed for the left side (-1.0 to 1.0)
  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_leftLeader.setControl(m_leftRequest.withOutput(leftSpeed));
    m_rightLeader.setControl(m_rightRequest.withOutput(rightSpeed));
  }

  /**
   * Command factory that continuously reads joystick suppliers for tank drive.
   */
  public Command tankDriveCommand(DoubleSupplier leftSpeedSupplier, DoubleSupplier rightSpeedSupplier) {
    return this.run(() -> tankDrive(leftSpeedSupplier.getAsDouble(), rightSpeedSupplier.getAsDouble()));
      }
  }
