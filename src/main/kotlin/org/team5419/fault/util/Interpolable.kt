package org.team5419.berkeleyLib.util

interface Interpolable<T> {
    public fun interpolate(other: T, x: Double): T
}
