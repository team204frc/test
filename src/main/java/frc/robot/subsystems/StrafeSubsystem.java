// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants204;

public class StrafeSubsystem extends SubsystemBase {
  private final StrafeModule m_frontLeft =
      new StrafeModule(
          Constants204.CAN.FL_DRIVE_MOTOR_ID,
          Constants204.CAN.FL_TURNING_MOTOR_ID
      );

  private final StrafeModule m_rearLeft =
      new StrafeModule(
          Constants204.CAN.RL_DRIVE_MOTOR_ID,
          Constants204.CAN.RL_TURNING_MOTOR_ID
      );

  private final StrafeModule m_frontRight =
      new StrafeModule(
          Constants204.CAN.FR_DRIVE_MOTOR_ID,
          Constants204.CAN.FR_TURNING_MOTOR_ID
      );

  private final StrafeModule m_rearRight =
      new StrafeModule(
          Constants204.CAN.RR_DRIVE_MOTOR_ID,
          Constants204.CAN.RR_TURNING_MOTOR_ID
      );

  public StrafeSubsystem() {}

  @Override
  public void periodic() {}

  public void drive(double forward, double strafe, double rot) {
      // forward unhandled
      double f = EQ.forward(forward);
      m_frontLeft.forward(f);
      m_frontRight.forward(f);
      m_rearLeft.forward(f);
      m_rearRight.forward(f);

      double s = EQ.strafe(strafe);
      m_frontLeft.strafe(s);
      m_frontRight.strafe(s);
      m_rearLeft.strafe(s);
      m_rearRight.strafe(s);

      // rot unhandled
      // FL-135 FR-45 RL-225 RR-315
      double r = EQ.rotate(rot);
      m_frontLeft.rotate(135, r);
      m_frontRight.rotate(45, r);
      m_rearLeft.rotate(225, r);
      m_rearRight.rotate(315, r);
  }

  public void setZero() {
      m_frontLeft.setZero();
      m_frontRight.setZero();
      m_rearLeft.setZero();
      m_rearRight.setZero();
  }

  private static class EQ {
      public static double forward(double in) {
          if (Math.abs(in) < Constants204.Controller.LEFT_Y_DEADBAND) {
              return 0.0;
          } else {
              return in;
          }
      }
      public static double strafe(double in) {
          if (Math.abs(in) < Constants204.Controller.LEFT_X_DEADBAND) {
              return 0.0;
          } else {
              return in;
          }
      }

      public static double rotate(double in) {
          if (Math.abs(in) < Constants204.Controller.RIGHT_X_DEADBAND) {
              return 0.0;
          } else {
              return in;
          }
      }
  }
}