package org.team5419.berkeleyLib.auto

import org.team5419.berkeleyLib.trajectory.types.Trajectory
import org.team5419.berkeleyLib.trajectory.types.TimedEntry
import org.team5419.berkeleyLib.math.geometry.Pose2dWithCurvature
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.Second
import org.team5419.berkeleyLib.subsystems.drivetrain.AbstractTankDrive
import org.team5419.berkeleyLib.trajectory.followers.TrajectoryFollower

public class DriveTrajectoryAction(
    private val drivetrain: AbstractTankDrive,
    private val trajectoryFollower: TrajectoryFollower,
    private val trajectory: Trajectory<SIUnit<Second>, TimedEntry<Pose2dWithCurvature>>
) : Action() {

    init {
        finishCondition += { trajectoryFollower.isFinished }
    }

    override fun start() {
        trajectoryFollower.reset(trajectory)
    }

    override fun update(dt: SIUnit<Second>) {
        drivetrain.setOutput(trajectoryFollower.nextState(drivetrain.robotPosition))
        // val referencePoint = trajectoryFollower.referencePoint
        // if (referencePoint != null) {
        //     val referencePose = referencePoint.state.state.pose
        // }
    }

    override fun finish() {
        drivetrain.zeroOutputs()
    }
}
