package frc.team5499.monkeyLib.path

import frc.team5499.monkeyLib.util.CSVWritable

import frc.team5499.monkeyLib.math.geometry.Vector2
import frc.team5499.monkeyLib.math.geometry.Pose2d
import frc.team5499.monkeyLib.math.geometry.Pose2dWithCurvature

class Path(
    points: MutableList<Pose2dWithCurvature>,
    velocities: MutableList<Double>,
    reversed: Boolean = false
) : CSVWritable {

    private val mPoints: MutableList<Pose2dWithCurvature>
    private val mVelocities: MutableList<Double>

    val pathLength: Int
        get() = mPoints.size

    val reversed: Boolean
        get() = field

    val startPose: Pose2dWithCurvature
        get() = Pose2dWithCurvature(mPoints.get(0))

    val endPose: Pose2dWithCurvature
        get() = Pose2dWithCurvature(mPoints.get(pathLength - 1))

    init {
        if (points.size != velocities.size) {
            println("coords length: ${points.size}, velo length: ${velocities.size}")
            throw IllegalArgumentException("Velocity and Coordinate arrays need to be same length.")
        }
        if (points.size < 2) throw IllegalArgumentException("Needs to be more than 2 points for a path")
        this.reversed = reversed
        mPoints = points.toMutableList()
        mVelocities = velocities.toMutableList()
    }

    constructor(other: Path): this(other.mPoints.toMutableList(), other.mVelocities, other.reversed)

    fun getPose(index: Int): Pose2dWithCurvature {
        if (index >= mPoints.size || index < 0) {
            throw IndexOutOfBoundsException("Point $index not in path")
        }
        return mPoints.get(index)
    }

    fun getVelocity(index: Int): Double {
        if (index >= mPoints.size || index < 0) {
            throw IndexOutOfBoundsException("Point $index not in velocities")
        }
        return mVelocities.get(index)
    }

    fun findClosestPointIndex(point: Pose2d, lastIndex: Int): Int {
        val lastPose: Vector2 = mPoints.get(lastIndex).translation
        var minDistance: Double = Vector2.distanceBetween(point.translation, lastPose)
        var index: Int = lastIndex
        for (i in lastIndex..mPoints.size - 1) {
            val tempDistance: Double = Vector2.distanceBetween(point.translation, mPoints.get(i).translation)
            if (tempDistance < minDistance) {
                index = i
                minDistance = tempDistance
            }
        }
        return index
    }

    override fun toString(): String {
        val buffer: StringBuilder = StringBuilder()
        for (i in 0..mPoints.size - 1) {
            buffer.append(mPoints.get(i).toString())
            buffer.append(System.lineSeparator())
        }
        return buffer.toString()
    }

    override fun toCSV(): String {
        val buffer: StringBuilder = StringBuilder()
        for (i in 0..mPoints.size - 1) {
            buffer.append(mPoints.get(i).pose.toCSV())
            buffer.append(", ")
            buffer.append(mVelocities.get(i))
            buffer.append(System.lineSeparator())
        }
        return buffer.toString()
    }
}