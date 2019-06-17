package org.team5499.monkeyLib.hardware

import edu.wpi.first.networktables.NetworkTableInstance
import org.team5499.monkeyLib.math.geometry.Rotation2d
import org.team5499.monkeyLib.math.geometry.degree
import org.team5499.monkeyLib.math.units.Length
import org.team5499.monkeyLib.math.units.inch
import org.team5499.monkeyLib.util.MovingAverageFilter
import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.tan

open class Limelight(
    networkTableName: String = "limelight",
    val inverted: Boolean = false,
    private val mTargetHeight: Length = 0.inch,
    private val mCameraHeight: Length = 0.inch,
    private val mCameraAngle: Rotation2d = 0.degree, // angle below (or above) horizontal
    skewFilterWeight: Double = 0.75
) {

    // FEEDBACK VARIABLES
    val targetFound: Boolean
        get() = mLimelight.getEntry("tv").getBoolean(false)

    val horizontalOffset: Double
        get() {
            if (inverted) {
                return -mLimelight.getEntry("tx").getDouble(0.0)
            } else {
                return mLimelight.getEntry("tx").getDouble(0.0)
            }
        }

    val verticalOffset: Double
        get() {
            if (inverted) {
                return -mLimelight.getEntry("ty").getDouble(0.0)
            } else {
                return mLimelight.getEntry("ty").getDouble(0.0)
            }
        }

    val targetArea: Double
        get() = mLimelight.getEntry("ta").getDouble(0.0)

    val targetSkew: Double
        get() {
            val skew = mLimelight.getEntry("ts").getDouble(0.0)
            if (skew < -45.0) {
                return mSkewFilter.update(skew + 90.0)
            } else {
                return mSkewFilter.update(skew)
            }
        }

    val horizontalLength: Double
        get() = mLimelight.getEntry("tlong").getDouble(0.0)

    val verticalLength: Double
        get() = mLimelight.getEntry("tvert").getDouble(0.0)

    val latency: Double
        get() = mLimelight.getEntry("tl").getDouble(0.0)

    // CALCULATED VARIABLES

    val horizontalDistance: Length
        get() = (mTargetHeight - mCameraHeight) / tan(mCameraAngle.radian + horizontalOffset.degree.radian)

    val calculateTargetSkew: Double
        get() {
            var temp = (horizontalLength / verticalLength) * kAspectRatio
            if (temp < 0.0) temp = 0.0
            if (temp > 1.0) temp = 1.0
            return (180.0 / PI) * asin(temp)
        }

    // LIGHT MODE
    var lightMode: LightMode = LightMode.Off
        set(value) {
            if (value == field) return
            when (value) {
                LightMode.On -> mLimelight.getEntry("ledMode").setNumber(3)
                LightMode.Off -> mLimelight.getEntry("ledMode").setNumber(1)
                LightMode.Blink -> mLimelight.getEntry("ledMode").setNumber(2)
            }
            field = value
        }

    // CAMERA MODE
    var cameraMode: CameraMode = CameraMode.Vision
        set(value) {
            if (field == value) return
            when (value) {
                CameraMode.Driver -> mLimelight.getEntry("camMode").setNumber(1)
                CameraMode.Vision -> mLimelight.getEntry("camMode").setNumber(0)
            }
            field = value
        }

    // STREAM Mode
    var streamMode: StreamMode = StreamMode.Standard
        set(value) {
            if (field == value) return
            when (value) {
                StreamMode.Standard -> mLimelight.getEntry("stream").setNumber(0)
                StreamMode.PipMain -> mLimelight.getEntry("stream").setNumber(1)
                StreamMode.PipSecondary -> mLimelight.getEntry("stream").setNumber(2)
            }
            field = value
        }

    // SNAPSHOT MODE
    var snapshotMode: SnapshotMode = SnapshotMode.Stop
        set(value) {
            if (field == value) return
            when (value) {
                SnapshotMode.Stop -> mLimelight.getEntry("snapshot").setNumber(0)
                SnapshotMode.Start -> mLimelight.getEntry("snapshot").setNumber(1)
            }
            field = value
        }

    // PIPELINE
    fun setPipeline(pipeline: Pipeline) = setPipeline(pipeline.id)
    fun setPipeline(id: Int) {
        if (id < 0 || id > 9) {
            println("Pipeline id needs to be from 0 to 9, ignoring value: $id")
            return
        } else {
            mLimelight.getEntry("pipeline").setNumber(id)
        }
    }

    private val mLimelight = NetworkTableInstance.getDefault().getTable(networkTableName)
    protected val mSkewFilter = MovingAverageFilter(skewFilterWeight)

    enum class LightMode { On, Off, Blink }
    enum class CameraMode { Vision, Driver }
    enum class StreamMode { Standard, PipMain, PipSecondary }
    enum class SnapshotMode { Stop, Start }

    data class Pipeline(val id: Int)

    companion object {
        const val kAspectRatio = 6.0 / 15.0
    }
}
