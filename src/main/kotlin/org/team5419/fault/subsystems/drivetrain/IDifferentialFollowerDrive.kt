package org.team5419.berkeleyLib.subsystems.drivetrain

import org.team5419.berkeleyLib.math.physics.DifferentialDrive
import org.team5419.berkeleyLib.math.units.derived.velocity
import org.team5419.berkeleyLib.math.units.derived.volts
import org.team5419.berkeleyLib.math.units.meters
import org.team5419.berkeleyLib.trajectory.followers.TrajectoryFollowerOutput

interface IDifferentialFollowerDrive : ITrajectoryFollowingDrive {

    val differentialDrive: DifferentialDrive

    override fun setOutput(output: TrajectoryFollowerOutput) {
        setOutputFromDynamics(
                output.differentialDriveVelocity,
                output.differentialDriveAcceleration
        )
    }

    fun setOutputFromKinematics(chassisVelocity: DifferentialDrive.ChassisState) {
        val wheelVelocities = differentialDrive.solveInverseKinematics(chassisVelocity)
        val feedForwardVoltages = differentialDrive.getVoltagesFromkV(wheelVelocities)

        setOutput(wheelVelocities, feedForwardVoltages)
    }

    fun setOutputFromDynamics(
        chassisVelocity: DifferentialDrive.ChassisState,
        chassisAcceleration: DifferentialDrive.ChassisState
    ) {
        val dynamics = differentialDrive.solveInverseDynamics(chassisVelocity, chassisAcceleration)

        setOutput(dynamics.wheelVelocity, dynamics.voltage)
    }

    fun setOutput(
        wheelVelocities: DifferentialDrive.WheelState,
        wheelVoltages: DifferentialDrive.WheelState
    ) {
        leftMasterMotor.setVelocity(
                (wheelVelocities.left * differentialDrive.wheelRadius).meters.velocity,
                wheelVoltages.left.volts
        )
        rightMasterMotor.setVelocity(
                (wheelVelocities.right * differentialDrive.wheelRadius).meters.velocity,
                wheelVoltages.right.volts
        )
    }
}
