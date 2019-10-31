package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.math.geometry.Pose2d

public class CubicBezairSpline(p0: Vector2, p1: Vector2, p2: Vector2, p3: Vector2) : Spline() {
    private val p0: Vector2
    private val p1: Vector2
    private val p2: Vector2
    private val p3: Vector2

    init{
        this.p0 = p0
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
    }

    fun x(t: Double): Double = Math.pow(1-t,3.0) * p0.x + 3 * Math.pow(1-t,2.0) * t * p1.x + 3 * (1-t) * Math.pow(t,2.0) * p2.x + Math.pow(t,3.0) * p3.x
    fun y(t: Double): Double = Math.pow(1-t,3.0) * p0.y + 3 * Math.pow(1-t,2.0) * t * p1.y + 3 * (1-t) * Math.pow(t,2.0) * p2.y + Math.pow(t,3.0) * p3.y
    fun dx(t: Double): Double = (1-t) * (1-t) * (p1.x - p0.x) + 2 * (1-t) * t * (p2.x - p1.x) + t * t * (p3.x - p2.x)
    fun dy(t: Double): Double = (1-t) * (1-t) * (p1.y - p0.y) + 2 * (1-t) * t * (p2.y - p1.y) + t * t * (p3.y - p2.y)
    fun ddx(t: Double): Double = 2*(t*(p3.x - 3 * p2.x + 3 * p1.x - p0.x) + p2.x - 2*p1.x + p0.x)
    fun ddy(t: Double): Double = 2*(t*(p3.y - 3 * p2.y + 3 * p1.y - p0.y) + p2.y - 2*p1.y + p0.y)

    // B(t) = (1 - t)^3P0 + 3(1 - t)^2tP1 + 3(1 - t)t^2P2 + t^3P3
    override public fun getPoint(t: Double) = Vector2(x(t), y(t))
    // B'(t) = 3(1- t)^2(P1 - P0) + 6(1 - t)t(P2 - P1) + 3t^2(P3 - P2)
    override public fun getHeading(t: Double) = Rotation2d(dx(t), dy(t), true)
    override fun getCurvature(t: Double): Double = ( (dx(t) * ddy(t)) - (dy(t) * ddx(t)) ) / Math.pow(Math.pow(dx(t), 2.0) + Math.pow(dy(t), 2.0), 3.0/2.0)
    override fun getDCurvature(t: Double) = 0.0
    override fun getVelocity(t: Double) = 0.0
}