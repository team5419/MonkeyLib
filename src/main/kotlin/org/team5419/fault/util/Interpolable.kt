package org.team5419.fault.util

/**
 * Will set an object to be "Interpolable".
 */
interface Interpolable<T> {
    /**
     * @param other Another value of type T
     * @param x A value from between 0 and 1.
     * @returns A value of type T
     */
    public fun interpolate(other: T, x: Double): T
}
