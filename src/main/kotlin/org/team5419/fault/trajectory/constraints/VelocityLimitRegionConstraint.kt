package org.team5419.berkeleyLib.trajectory.constraints

import org.team5419.berkeleyLib.math.geometry.Pose2dWithCurvature
import org.team5419.berkeleyLib.math.geometry.Rectangle2d
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.LinearVelocity

public class VelocityLimitRegionConstraint constructor(
    private val region: Rectangle2d,
    private val velocityLimit: SIUnit<LinearVelocity>
) : TimingConstraint<Pose2dWithCurvature> {

    override fun getMaxVelocity(state: Pose2dWithCurvature) =
        if (state.pose.translation in region) velocityLimit.value else Double.POSITIVE_INFINITY

    override fun getMinMaxAcceleration(state: Pose2dWithCurvature, velocity: Double) =
        TimingConstraint.MinMaxAcceleration.noLimits
}
