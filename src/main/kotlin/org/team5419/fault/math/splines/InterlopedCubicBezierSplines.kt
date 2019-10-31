package org.team5419.fault.math.splines

import org.team5419.fault.math.splines.Spline
import org.team5419.fault.math.splines.CubicBezierSpline
import org.team5419.fault.math.geometry.Vector2
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.util.Oof


public class InterlopedCubicBezierSplines(vararg splines: CubicBezierSpline): Spline() {
    private val splines: ArrayList<CubicBezierSpline> = ArrayList<CubicBezierSpline>()
    private val points: ArrayList<Vector2> = ArrayList<Vector2>()

    /*constructor(vararg points: Vector2) : this() {
		require(points.count() >= 4 && (points.count()-1) % 3 == 0) { "Insufficant number of points" }
		for(point in points) this.points.add(point)
        for(i in 3..points.count() step 3){
        	this.splines.add(CubicBezierSpline(
				this.points.get(i-3),
				this.points.get(i-2),
				this.points.get(i-1),
				this.points.get(i)
    		))
    	}
	}*/

    init{
		require(splines.count() > 0) {"Need at least one spline"}
		var lastAnchor: Vector2 = this.splines.get(0).p0
		this.points.add(lastAnchor)
        for(spline in this.splines) {
			if(spline.p0 != lastAnchor){
				if(spline.p0.equals(lastAnchor)) {
					spline.p0 = lastAnchor
				} else throw Oof("Splines not interloped")
			}
			this.points.add(spline.p1)
			this.points.add(spline.p2)
			this.points.add(spline.p3)
			lastAnchor = spline.p3
		}
	}

    private fun getSpline(t: Double): CubicBezierSpline = this.splines.get( (t * this.splines.count()).toInt() )
    private fun getRelativeT(t: Double): Double = (t * this.splines.count()) - (t * this.splines.count()).toInt()

    override public fun getPoint(t: Double): Vector2 = getSpline(t).getPoint(getRelativeT(t))
    override public fun getHeading(t: Double): Rotation2d = getSpline(t).getHeading(getRelativeT(t))
    override public fun getCurvature(t: Double): Double = getSpline(t).getCurvature(getRelativeT(t))
    override public fun getDCurvature(t: Double): Double = getSpline(t).getDCurvature(getRelativeT(t))
	override public fun getVelocity(t: Double): Double = getSpline(t).getVelocity(getRelativeT(t))
}
