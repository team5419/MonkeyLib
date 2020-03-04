package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2d
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.util.Oof

public class InterlopedBezierSpline(vararg splines: BezierSpline) : Spline() {

    private val splines: Array<out BezierSpline>
    private val numSplines: Int
        get() = splines.size

    init { this.splines = splines }

    fun getSpline(t: Double): BezierSpline = this.splines[(t * this.numSplines).toInt()]
    fun getReletiveT(t: Double): Double = (t * this.numSplines) - (t * this.numSplines).toInt()

    override fun getPoint(t: Double): Vector2d = this.getSpline(t).getPoint(this.getReletiveT(t))
    override fun getHeading(t: Double): Rotation2d = this.getSpline(t).getHeading(this.getReletiveT(t))
    override fun getVelocity(t: Double): Double = this.getSpline(t).getVelocity(this.getReletiveT(t))

    override fun getCurvature(t: Double): Double = this.getSpline(t).getCurvature(this.getReletiveT(t))
    override fun getDCurvature(t: Double): Double = this.getSpline(t).getDCurvature(this.getReletiveT(t))
}
