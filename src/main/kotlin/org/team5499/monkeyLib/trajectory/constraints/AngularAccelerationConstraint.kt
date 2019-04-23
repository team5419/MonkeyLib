package org.team5499.monkeyLib.trajectory.constraints

import org.team5499.monkeyLib.math.geometry.Pose2dWithCurvature

import kotlin.math.absoluteValue
import kotlin.math.sqrt
import kotlin.math.abs

class AngularAccelerationConstraint internal constructor(
    val maxAngularAcceleration: Double
) : TimingConstraint<Pose2dWithCurvature> {

    init {
        require(maxAngularAcceleration > 0.0) {
            "Cannot have a negative angular acceleration"
        }
    }

    override fun getMaxVelocity(state: Pose2dWithCurvature): Double {
        return sqrt(maxAngularAcceleration / state.dCurvature.absoluteValue)
    }

    override fun getMinMaxAcceleration(
        state: Pose2dWithCurvature,
        velocity: Double
    ): TimingConstraint.MinMaxAcceleration {
        val maxAbsoluteAcceleration = abs(
            (maxAngularAcceleration - (velocity * velocity * state.dCurvature)) / state.curvature
        )
        return TimingConstraint.MinMaxAcceleration(-maxAbsoluteAcceleration, maxAbsoluteAcceleration)
    }
}
