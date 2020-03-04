package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2d
import org.team5419.fault.math.geometry.Rotation2d

class BezierSpline(startPoint: Vector2d, vararg controlPoints: Vector2d, endPoint: Vector2d) : Spline() {
    
    val startPoint: Vector2d
    val endPoint: Vector2d
    val order: Int
    private val points: Array<Vector2d>


    private fun fact(n: Int): Int = if(n == 0) 1 else n * fact(n - 1)

    init {
        this.startPoint = startPoint
        this.endPoint = endPoint
        this.points = Array<Vector2d>(controlPoints.size + 2) { 
            i -> if (i == 0) startPoint else if (i == controlPoints.size+1) endPoint else controlPoints.get(i) 
        }
        this.order = points.size + 2
    }

    public override fun getPoint(u: Double): Vector2d {
        return points.mapIndexed({ i: Int, point: Vector2d -> 
            point * fact(order) / fact(i) / fact(order-i) * Math.pow(u, i as Double) * Math.pow(1-u, order - i as Double)
        }).reduce({ sum, nextPoint ->
            sum + nextPoint
        })
        
    }

    public override fun getHeading(t: Double): Rotation2d {
        return Rotation2d()
    }

    override fun getCurvature(t: Double): Double = 0.0
    override fun getDCurvature(t: Double): Double = 0.0
    override fun getVelocity(t: Double): Double = 0.0
}
