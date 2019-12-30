package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2d
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.util.Oof

public class InterlopedCubicBezierSpline(vararg points: Vector2d) : Spline() {
    private val splines: Array<CubicBezierSpline>
    private val points: Array<out Vector2d>
    private val numSplines: Int
        get() = this.splines.size

    init {
        if (points.count() % 3 != 1) throw Oof("Inncorrect number of points")
        this.points = points
        this.splines = Array<CubicBezierSpline>((points.count() - 1) / 3) {
            i -> CubicBezierSpline(
                points[i * 3],
                points[i * 3 + 1],
                points[i * 3 + 2],
                points[i * 3 + 3]
            )
        }
    }

    fun getSpline(t: Double): CubicBezierSpline = this.splines[(t * this.numSplines).toInt()]
    fun getReletiveT(t: Double): Double = (t * this.numSplines) - (t * this.numSplines).toInt()

    override fun getPoint(t: Double): Vector2d = this.getSpline(t).getPoint(this.getReletiveT(t))
    override fun getHeading(t: Double): Rotation2d = this.getSpline(t).getHeading(this.getReletiveT(t))
    override fun getVelocity(t: Double): Double = this.getSpline(t).getVelocity(this.getReletiveT(t))

    override fun getCurvature(t: Double): Double = this.getSpline(t).getCurvature(this.getReletiveT(t))
    override fun getDCurvature(t: Double): Double = this.getSpline(t).getDCurvature(this.getReletiveT(t))
}
