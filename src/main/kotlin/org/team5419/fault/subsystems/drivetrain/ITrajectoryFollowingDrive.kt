package org.team5419.berkeleyLib.subsystems.drivetrain

import org.team5419.berkeleyLib.hardware.LinearBerkeleyMotor
import org.team5419.berkeleyLib.math.geometry.Pose2d
import org.team5419.berkeleyLib.trajectory.followers.TrajectoryFollower
import org.team5419.berkeleyLib.trajectory.followers.TrajectoryFollowerOutput

interface ITrajectoryFollowingDrive {

    val leftMasterMotor: LinearBerkeleyMotor
    val rightMasterMotor: LinearBerkeleyMotor

    val trajectoryFollower: TrajectoryFollower

    val robotPosition: Pose2d

    fun setOutput(output: TrajectoryFollowerOutput)

    fun zeroOutputs() {
        leftMasterMotor.setNeutral()
        rightMasterMotor.setNeutral()
    }
}
