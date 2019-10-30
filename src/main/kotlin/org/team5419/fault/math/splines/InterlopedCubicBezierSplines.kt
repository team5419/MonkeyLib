package org.team5419.fault.math.splines

import org.team5419.fault.math.splines.Spline
import org.team5419.fault.math.splines.CubicBezierSpline
import rg.team5419.fault.geometry.Vector2
import org.team5419.fault.geometry.Rotation2d

public class InterlopedCubicBezierSplines: Spline(vararg splines: CubicBezierSpline) {
    private val splines: Array<CubicBezierSpline>
    private val numSplines: Int
      get() = splines.size

    init{
        this.splines = splines
    }

    private fun getSpline(t: Double): CubicBezierSpline = this.splines.get( (t * this.numSplines).toInt() )
    private fun getRelativeT(t: Double): Double = (t * this.numSplines) - (t * this.numSplines).toInt()

    override public fun getPoint(t: Double): Vector2 = getSpline(t).getPoint(getRelativeT(t))
    override public fun getHeading(t: Double): Vector2 = getSpline(t).getHeading(getRelativeT(t))
    override public fun getCurvature(t: Double): Vector2 = getSpline(t).getCurvature(getRelativeT(t))

}
