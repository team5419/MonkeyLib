package org.team5419.frc2020.auto

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.RobotController
import edu.wpi.first.networktables.NetworkTableInstance
import org.team5419.fault.subsystems.drivetrain.AbstractTankDrive
import org.team5419.fault.auto.Action

public class CharacterizationAction(val driveTrain: AbstractTankDrive) : Action() {
    val autoSpeedEntry = NetworkTableInstance.getDefault().getEntry("/robot/autospeed")
    val telemetryEntry = NetworkTableInstance.getDefault().getEntry("/robot/telemetry")

    var priorAutospeed = 0.0

    override fun start() {
    }

    override fun update() {
        val now = Timer.getFPGATimestamp()

        val battery = RobotController.getBatteryVoltage()

        val autospeed = autoSpeedEntry.getDouble(0.0)

        driveTrain.setPercent(autospeed, autospeed)

        val leftPosition = driveTrain.leftDistance
        val leftRate = driveTrain.leftVelocity

        val rightPosition = driveTrain.rightDistance
        val rightRate = driveTrain.rightVelocity

        val leftMotorVolts = battery * Math.abs(priorAutospeed)
        val rightMotorVolts = battery * Math.abs(priorAutospeed)

        val data: Array<Double> = arrayOf<Double>(
            now,
            battery,
            autospeed,

            leftMotorVolts,
            rightMotorVolts,

            leftPosition.value,
            rightPosition.value,

            leftRate.value,
            rightRate.value
        )

        telemetryEntry.setNumberArray(data)

        priorAutospeed = autospeed
    }

    override fun finish() {
    }
}
