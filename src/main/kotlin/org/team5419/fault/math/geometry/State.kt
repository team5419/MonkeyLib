package org.team5419.berkeleyLib.math.geometry

import org.team5419.berkeleyLib.util.Interpolable
import org.team5419.berkeleyLib.util.CSVWritable

interface State<T> : Interpolable<T>, CSVWritable {

    fun distance(other: T): Double

    public override fun toString(): String

    public override fun toCSV(): String

    public override fun equals(other: Any?): Boolean

    public override fun interpolate(other: T, x: Double): T

    public override fun hashCode(): Int
}
