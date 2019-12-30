package org.team5419.fault.math.splines

import org.team5419.fault.math.geometry.Vector2d
import org.team5419.fault.math.geometry.Rotation2d

class CubicBezierSpline(p0: Vector2d, p1: Vector2d, p2: Vector2d, p3: Vector2d) : Spline() {
    public var p0: Vector2d
    public var p1: Vector2d
    public var p2: Vector2d
    public var p3: Vector2d

    init {
        this.p0 = p0
        this.p1 = p1
        this.p2 = p2
        this.p3 = p3
    }

    // B(t) = (1 - t)^3P0 + 3(1 - t)^2tP1 + 3(1 - t)t^2P2 + t^3P3
    public override fun getPoint(t: Double): Vector2d {
        val px =
            p0.x * Math.pow(1 - t, 3.0) +
            p1.x * 3 * Math.pow(1 - t, 2.0) * t +
            p2.x * 3 * (1 - t) * Math.pow(t, 2.0) +
            p3.x * Math.pow(t, 3.0)

        val py =
            p0.y * Math.pow(1 - t, 3.0) +
            p1.y * 3 * Math.pow(1 - t, 2.0) * t +
            p2.y * 3 * (1 - t) * Math.pow(t, 2.0) +
            p3.y * Math.pow(t, 3.0)

        return Vector2d(px, py)
    }

    // B'(t) = 3(1- t)^2(P1 - P0) + 6(1 - t)t(P2 - P1) + 3t^2(P3 - P2)
    public override fun getHeading(t: Double): Rotation2d {
        val px = (p1.x - p0.x) * (1 - t) * (1 - t) + (p2.x - p1.x) * 2 * (1 - t) * t + (p3.x - p2.x) * t * t
        val py = (p1.y - p0.y) * (1 - t) * (1 - t) + (p2.y - p1.y) * 2 * (1 - t) * t + (p3.y - p2.y) * t * t
        return Rotation2d(px.value, py.value, true)
    }

    override fun getCurvature(t: Double): Double = 0.0
    override fun getDCurvature(t: Double): Double = 0.0
    override fun getVelocity(t: Double): Double = 0.0
}
