package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.math.geometry.Pose2d
package org.team5419.fault.math.splines.Spline


class BerzairCubicSpline(
    
) Spline {

    private val ax: Double
    private val bx: Double
    private val cx: Double
    private val dx: Double

    private val ay: Double
    private val by: Double
    private val cy: Double
    private val dy: Double

    init{

    }

    override public fun getPoint(t): Vector2 {
        val endIndex = getAnchors() - ceil(t)
        val d = points.get(endIndex)
        val c = points.get(endIndex - 1)
        val b = points.get(endIndex - 2)
        val a = points.get(endIndex - 3)
        return cerp(a,b,c,d,t)
    }

    override public fun getCurvature(t): Double {
        Math.sqrt()
    }


    //get direction radian 
    override fun getHeadingAtWaypoint(t: Double): Rotation2d {
        val wayPointIndex = getWaypointIndex(t)
        val p0 = points.get(wayPointIndex)
        val p1 = points.get(wayPointIndex - 1) 
        return Math.atan( (p1.y - p0.y) / (p1.x - p0.x) )
    }

    override fun getCurvature(t: Double): Double

    override fun getDCurvature(t: Double): Double

    override fun getVelocity(t: Double): Double

}