package org.team5419.fault.math.splines

import org.team5419.fault.math.splines.Spline
import org.team5419.fault.math.splines.CubicBezierSpline
import org.team5419.fault.geometry.Vector2
import org.team5419.fault.geometry.Rotation2d

public class InterlopedCubicBezierSplines: Spline(vararg splines: CubicBezierSpline) {
    private val splines: Array<CubicBezierSpline>
    private val numSplines: Int
      get() = splines.size

    init{
        this.splines = splines
        //TODO confirm interlopation
    }

    private fun getSpline(t: Double): CubicBezierSpline = this.splines.get( (t * this.numSplines).toInt() )
    private fun getRelativeT(t: Double): Double = (t * this.numSplines) - (t * this.numSplines).toInt()

    override public fun getPoint(t: Double): Vector2 = getSpline(t).getPoint(getRelativeT(t))
    override public fun getHeading(t: Double): Rotation2d = getSpline(t).getHeading(getRelativeT(t))
    override public fun getCurvature(t: Double): Double = getSpline(t).getCurvature(getRelativeT(t))
    override public fun getDCurvature(t: Double): Double = getSpline(t).getDCurvature(getRelativeT(t))
}
