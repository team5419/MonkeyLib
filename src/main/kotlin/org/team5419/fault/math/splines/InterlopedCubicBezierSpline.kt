package org.team5419.fault.math.splines

import org.team5419.fault.math.splines.Spline
import org.team5419.fault.math.splines.CubicBezierSpline
import org.team5419.fault.math.geometry.Vector2
import org.team5419.fault.math.geometry.Rotation2d
import org.team5419.fault.util.Oof


public class InterlopedCubicBezierSpline (vararg splines: CubicBezierSpline): Spline() {
    private val splines: Array<CubicBezierSpline>
    private val points: Array<Vector2>
    private val numSplines: Int
        get() = this.splines.size


    constructor(vararg points){
        if(points.count() % 3 != 1) throw Oof("Inncorrect number of points")
        this.points = points
    }
    init{
        this.splines = splines
        //TODO: add rotate to interlope
    }

    fun getSpline(t: Double): CubicBezierSpline = this.splines[(t * this.numSplines).toInt()]

    fun getT(t: Double): Double = (t * this.numSplines) - (t * this.numSplines).toInt()

    override fun getPoint(t: Double): Vector2 = this.getSpline(t).getPoint(this.getT(t))
    override fun getHeading(t: Double): Vector2 = this.getSpline(t).getHeading(this.getT(t))
    override fun getCurvature(t: Double): Vector2 = this.getSpline(t).getCurvature(this.getT(t))


}