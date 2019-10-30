package org.team5419.fault.math.splines

import org.team5419.fault.math.splines.Spline
import org.team5419.fault.math.splines.CubicBezierSpline
import org.team5419.fault.geometry.Vector2
import org.team5419.fault.geometry.Rotation2d
import org.team5419.fault.util.Oof


public class InterlopedCubicBezierSplines: Spline(vararg splines: CubicBezierSpline) {
    private val splines: Array<CubicBezierSpline>
    private val points: Array<Vector2>

    constructor(vararg points: Vector2){
        if(points.count() < 4 || (points.count()-1) % 3 != 0){
          throw Oof("Insufficant number of points")
        }
        this.points = points
        for(i in 3..points.count() step 3){
        	splines.add(BerzairCubicSpline(

    		))
    	}
	}

    init{
        this.splines = splines
        for(i in 1..this.splines.count()-1){
          if(!this.splines.get(i-1).isInterloped(this.splines.get(i)))
            throw Oof("Splines not interloped")
        }
    }

    private fun getSpline(t: Double): CubicBezierSpline = this.splines.get( (t * this.splines.count()).toInt() )
    private fun getRelativeT(t: Double): Double = (t * this.splines.count()) - (t * this.splines.count()).toInt()

    override public fun getPoint(t: Double): Vector2 = getSpline(t).getPoint(getRelativeT(t))
    override public fun getHeading(t: Double): Rotation2d = getSpline(t).getHeading(getRelativeT(t))
    override public fun getCurvature(t: Double): Double = getSpline(t).getCurvature(getRelativeT(t))
    override public fun getDCurvature(t: Double): Double = getSpline(t).getDCurvature(getRelativeT(t))
}
