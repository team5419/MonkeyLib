package org.team5419.berkeleyLib.trajectory.constraints

import org.team5419.berkeleyLib.math.physics.DifferentialDrive
import org.team5419.berkeleyLib.math.geometry.Pose2dWithCurvature
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.Volt

class DifferentialDriveDynamicsConstraint constructor(
    private val drive: DifferentialDrive,
    private val maxVoltage: SIUnit<Volt>
) : TimingConstraint<Pose2dWithCurvature> {

    override fun getMaxVelocity(state: Pose2dWithCurvature) =
        drive.getMaxAbsVelocity(state.curvature, maxVoltage.value)

    override fun getMinMaxAcceleration(
        state: Pose2dWithCurvature,
        velocity: Double
    ): TimingConstraint.MinMaxAcceleration {
        val minMax = drive.getMinMaxAcceleration(
            DifferentialDrive.ChassisState(velocity, velocity * state.curvature),
            state.curvature,
            maxVoltage.value
        )
        return TimingConstraint.MinMaxAcceleration(minMax.min, minMax.max)
    }
}
