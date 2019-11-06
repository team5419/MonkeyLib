package org.team5419.fault.util

/**
 *
 * @param maxSize The max size of the buffer.
 */
public open class CircularBuffer<T>(maxSize: Int) {

    private val mMaxSize: Int

    public val elements: MutableList<T>

    init {
        mMaxSize = maxSize
        if (mMaxSize <= 0) {
            throw IllegalArgumentException("maxSize must be a positive integer.")
        }
        elements = mutableListOf()
    }

    /**
     * Adds a value to the buffer.
     * @param element The element that you want to add to the buffer.
     * @returns The value that has been removed from the buffer, or null if it doesn't exist.
     */
    public fun add(element: T): T? {
        elements.add(element)
        if (elements.size > mMaxSize) {
            val poppedValue = elements.removeAt(0)
            return poppedValue
        }
        return null
    }

    /**
     * Clears the whole buffer, effectively removing all the values inside the buffer.
     */
    public fun clear() {
        elements.clear()
    }
}
