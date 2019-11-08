package org.team5419.fault.util.loops

/**
 * Creates an object which is able to handle many loops.
 */
public interface ILooper {

    /**
     * Registers a loop in the Looper.
     * @param loop The loop which you want to register.
     */
    fun register(loop: Loop)
}
