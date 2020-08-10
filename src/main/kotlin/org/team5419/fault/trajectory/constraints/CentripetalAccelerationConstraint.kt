package org.team5419.berkeleyLib.trajectory.constraints

import org.team5419.berkeleyLib.math.geometry.Pose2dWithCurvature
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.LinearAcceleration

import kotlin.math.absoluteValue
import kotlin.math.sqrt

class CentripetalAccelerationConstraint constructor(
    private val maxCentripetalAcceleration: SIUnit<LinearAcceleration>
) : TimingConstraint<Pose2dWithCurvature> {

    override fun getMaxVelocity(state: Pose2dWithCurvature) =
        sqrt((maxCentripetalAcceleration.value / state.curvature).absoluteValue)

    override fun getMinMaxAcceleration(
        state: Pose2dWithCurvature,
        velocity: Double
    ) = TimingConstraint.MinMaxAcceleration.noLimits
}
