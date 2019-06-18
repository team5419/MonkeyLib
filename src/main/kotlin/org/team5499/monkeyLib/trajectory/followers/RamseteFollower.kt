package org.team5499.monkeyLib.trajectory.followers

import org.team5499.monkeyLib.trajectory.TrajectoryIterator
import org.team5499.monkeyLib.trajectory.types.TimedEntry
import org.team5499.monkeyLib.math.geometry.Pose2dWithCurvature
import org.team5499.monkeyLib.math.geometry.Pose2d

import org.team5499.monkeyLib.math.epsilonEquals
import org.team5499.monkeyLib.math.units.Time

import kotlin.math.sqrt
import kotlin.math.sin

class RamseteFollower(
    private val kBeta: Double,
    private val kZeta: Double
) : TrajectoryFollower() {

    override fun calculateState(
        iterator: TrajectoryIterator<Time, TimedEntry<Pose2dWithCurvature>>,
        robotPose: Pose2d
    ): TrajectoryFollower.TrajectoryFollowerVelocityOutput {
        val referenceState = iterator.currentState.state

        // Calculate goal in robot's coordinates
        val error = referenceState.state.pose inFrameOfReferenceOf robotPose

        // Get reference linear and angular velocities
        val vd = referenceState._velocity
        val wd = vd * referenceState.state.curvature

        // Compute gain
        val k1 = 2 * kZeta * sqrt(wd * wd + kBeta * vd * vd)

        // Get angular error in bounded radians
        val angleError = error.rotation.radian

        return TrajectoryFollower.TrajectoryFollowerVelocityOutput(
            _linearVelocity = vd * error.rotation.cos + k1 * error.translation.x,
            _angularVelocity = wd + kBeta * vd * sinc(angleError) * error.translation.y + k1 * angleError
        )
    }

    companion object {
        private fun sinc(theta: Double) =
            if (theta epsilonEquals 0.0) {
                1.0 - 1.0 / 6.0 * theta * theta
            } else sin(theta) / theta
    }
}
