package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2d
import org.team5419.fault.math.geometry.Rotation2d

class BezierSpline(startPoint: Vector2d, vararg controlPoints: Vector2d, endPoint: Vector2d) : Spline() {
    
    val startPoint: Vector2d
    val endPoint: Vector2d
    val order: Int
    private val points: Array<Vector2d>


    private val fact(i: Int) = if(i==0) 1 else i * fact(i-1)

    init {
        this.startPoint = startPoint
        this.endPoint = endPoint
        this.points = Array<Vector2d>(points.length + 2) { 
            i -> if (i == 0) startPoint else if (i == points.length+1) endPoint else points.get(i) 
        }
        this.order = points.length
    }

    public override fun getPoint(u: Double): Vector2d {
        points.map({ fact(order) })
        
    }

    public override fun getHeading(t: Double): Rotation2d {
        
    }

    override fun getCurvature(t: Double): Double = 0.0
    override fun getDCurvature(t: Double): Double = 0.0
    override fun getVelocity(t: Double): Double = 0.0
}
