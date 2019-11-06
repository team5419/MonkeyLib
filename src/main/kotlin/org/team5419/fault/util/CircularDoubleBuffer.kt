package org.team5419.fault.util

/**
 * It is essentially a CircularBuffer, except uses Doubles.
 * @param maxSize The max size of the buffer.
 */
public class CircularDoubleBuffer(maxSize: Int) : CircularBuffer<Double>(maxSize) {

    /**
     * The sum of the values in the buffer.
     */
    public val sum: Double
        get() {
            var total = 0.0
            for (num in super.elements) {
                total += num
            }
            return total
        }

    /**
     * The average of the values in the buffer.
     */
    public val average: Double
        get() {
            return sum / super.elements.size.toDouble()
        }
}
