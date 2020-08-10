package org.team5419.berkeleyLib.trajectory.constraints

import org.team5419.berkeleyLib.math.geometry.Pose2dWithCurvature
import org.team5419.berkeleyLib.math.geometry.Vector2d
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.LinearVelocity

class VelocityLimitRadiusConstraint constructor(
    private val point: Vector2d,
    private val radius: SIUnit<Meter>,
    private val velocityLimit: SIUnit<LinearVelocity>
) : TimingConstraint<Pose2dWithCurvature> {

    override fun getMaxVelocity(state: Pose2dWithCurvature) =
        if (state.pose.translation.distance(point) <= radius.value) velocityLimit.value else Double.POSITIVE_INFINITY

    override fun getMinMaxAcceleration(
        state: Pose2dWithCurvature,
        velocity: Double
    ) = TimingConstraint.MinMaxAcceleration.noLimits
}
