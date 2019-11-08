package org.team5419.fault.util.loops

/**
 * Creates a loopable object.
 */
public interface Loop {

    /**
     * This function is when the loop starts.
     * @param timestamp Tells the function at what time to run this function.
     */
    public fun onStart(timestamp: Double)

    /**
     * This function is run during the loop.
     * @param timestamp Tells the function at what time to run this function.
     */
    public fun onLoop(timestamp: Double)

    /**
     * This function is run when the loop stops.
     * @param timestamp Tells the function at what time to run this function.
     */
    public fun onStop(timestamp: Double)
}
