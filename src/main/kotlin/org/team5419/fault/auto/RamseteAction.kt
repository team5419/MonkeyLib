package org.team5419.fault.auto

import org.team5419.fault.auto.Action
import org.team5419.fault.math.units.SIUnit
import org.team5419.fault.math.units.Meter
import org.team5419.fault.math.units.meters
import org.team5419.fault.math.units.inMeters
import org.team5419.fault.math.units.seconds
import org.team5419.fault.math.units.inSeconds
import org.team5419.fault.math.units.derived.LinearVelocity
import org.team5419.fault.math.units.derived.velocity
import org.team5419.fault.math.units.derived.LinearAcceleration
import org.team5419.fault.math.units.derived.Volt
import org.team5419.fault.math.units.derived.volts
import org.team5419.fault.subsystems.drivetrain.AbstractTankDrive
import edu.wpi.first.wpilibj.controller.RamseteController
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj.geometry.Pose2d
import edu.wpi.first.wpilibj.geometry.Rotation2d
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds
import edu.wpi.first.wpilibj.trajectory.Trajectory
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveKinematicsConstraint

// refrences:
// https://github.com/wpilibsuite/allwpilib/blob/master/wpilibNewCommands/src/main/java/edu/wpi/first/wpilibj2/command/RamseteCommand.java
// https://docs.wpilib.org/en/latest/docs/software/examples-tutorials/trajectory-tutorial/index.html

public class RamseteAction(
    val drivetrain: AbstractTankDrive,

    val trajectory: Trajectory,

    val maxVelocity: SIUnit<LinearVelocity>,
    val maxAcceleration: SIUnit<LinearAcceleration>,
    val maxVoltage: SIUnit<Volt>,

    val trackWidth: SIUnit<Meter>,

    val beta: Double,
    val zeta: Double,

    val kS: Double,
    val kV: Double,
    val kA: Double
) : Action() {

    val driveKinematics = DifferentialDriveKinematics(trackWidth.inMeters())

    val feedforward = SimpleMotorFeedforward(kS, kV, kA)

    val voltageConstraint = DifferentialDriveVoltageConstraint(
        feedforward,
        driveKinematics,
        maxVoltage.value
    )

    val driveKinematicsConstraint = DifferentialDriveKinematicsConstraint(
        driveKinematics,
        maxVelocity.value
    )

    val config = TrajectoryConfig(
        maxVelocity.value,
        maxAcceleration.value
    )

    init {
        config.setKinematics(driveKinematics)
        config.addConstraint(voltageConstraint)
        config.addConstraint(driveKinematicsConstraint)
    }

    val controller = RamseteController(beta, zeta)

    var prevTime = 0.0.seconds
    var prevSpeed = DifferentialDriveWheelSpeeds(0.0, 0.0)

    init {
        finishCondition += { getTime() > trajectory.getTotalTimeSeconds() }
    }

    override fun start() {
        super.start()
    }

    override fun update() {
        val time = getTime()
        val dt = time - prevTime

        val chassisSpeed = controller.calculate(
            Pose2d(
                drivetrain.leftDistance.inMeters(),
                drivetrain.rightDistance.inMeters(),
                Rotation2d(drivetrain.robotPosition.rotation.radian.value)
            ),

            trajectory.sample(time.inSeconds())
        )

        val setSpeed = driveKinematics.toWheelSpeeds(chassisSpeed)

        val leftFeedForward = feedforward.calculate(
            setSpeed.leftMetersPerSecond,
            (setSpeed.leftMetersPerSecond - prevSpeed.leftMetersPerSecond) / dt.value
        )

        val rightFeedForward = feedforward.calculate(
            setSpeed.rightMetersPerSecond,
            (setSpeed.rightMetersPerSecond - prevSpeed.rightMetersPerSecond) / dt.value
        )

        prevSpeed = setSpeed

        drivetrain.setVelocity(
            setSpeed.leftMetersPerSecond.meters.velocity,
            setSpeed.rightMetersPerSecond.meters.velocity,
            leftFeedForward.volts,
            rightFeedForward.volts
        )
    }

    override fun finish() {
        drivetrain.setPercent(0.0, 0.0)
    }
}
