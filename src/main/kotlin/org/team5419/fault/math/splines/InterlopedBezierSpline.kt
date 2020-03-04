package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2d
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.util.Oof

public class InterlopedBezierSpline(vararg splines: BezierSpline) : Spline() {

    private val splines: Array<out BezierSpline>
    private val numSplines: Int
        get() = splines.size

    init { this.splines = splines }

    fun getSpline(u: Double): BezierSpline = this.splines[(u * this.numSplines).toInt()]
    fun getReletiveT(u: Double): Double = (u * this.numSplines) - (u * this.numSplines).toInt()

    override fun getPoint(u: Double): Vector2d = this.getSpline(u).getPoint(this.getReletiveT(u))
    override fun getHeading(u: Double): Rotation2d = this.getSpline(u).getHeading(this.getReletiveT(u))
    override fun getVelocity(u: Double): Double = this.getSpline(u).getVelocity(this.getReletiveT(u)) //TO FIX

    override fun getCurvature(u: Double): Double = this.getSpline(u).getCurvature(this.getReletiveT(u))
    override fun getDCurvature(u: Double): Double = this.getSpline(u).getDCurvature(this.getReletiveT(u))
}
