package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.math.geometry.Pose2d

class CubicBezierSpline(p0: Vector2, p1: Vector2, p2: Vector2, p3: Vector2) : Spline() {
    public val p0: Vector2
    public val p1: Vector2
    public val p2: Vector2
    public val p3: Vector2

    init{
        this.p0 = p0
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
    }

    // B(t) = (1 - t)^3P0 + 3(1 - t)^2tP1 + 3(1 - t)t^2P2 + t^3P3
    override public fun getPoint(t: Double): Vector2 {
        val px = Math.pow(1-t,3.0) * p0.x + 3 * Math.pow(1-t,2.0) * t * p1.x + 3 * (1-t) * Math.pow(t,2.0) * p2.x + Math.pow(t,3.0) * p3.x
        val py = Math.pow(1-t,3.0) * p0.y + 3 * Math.pow(1-t,2.0) * t * p1.y + 3 * (1-t) * Math.pow(t,2.0) * p2.y + Math.pow(t,3.0) * p3.y
        return Vector2(px, py)
    }

    // B'(t) = 3(1- t)^2(P1 - P0) + 6(1 - t)t(P2 - P1) + 3t^2(P3 - P2)
    override public fun getHeading(t: Double): Rotation2d {
        val px = (1-t) * (1-t) * (p1.x - p0.x) + 2 * (1-t) * t * (p2.x - p1.x) + t * t * (p3.x - p2.x)
        val py = (1-t) * (1-t) * (p1.y - p0.y) + 2 * (1-t) * t * (p2.y - p1.y) + t * t * (p3.y - p2.y)
        return Rotation2d(px, py, true)
    }

    override fun getCurvature(t: Double): Double = 0.0
    override fun getDCurvature(t: Double) = 0.0
    override fun getVelocity(t: Double) = 0.0

    fun isInterloped(other: CubicBezierSpline): Boolean {
        val slope = Math.atan(this.p3.y - this.p2.y, this.p3.x - this.p2.x)
        return (
          this.p3.equals(other.p3) &&
          Math.atan(other.p1.y - this.p2.y, other.p1.x - this.p2.x) == slope &&
          Math.atan(other.p1.y - this.p3.y, other.p1.x - this.p3.x) == slope &&
        )

      }

}
