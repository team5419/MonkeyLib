package org.team5419.fault.util.time

import edu.wpi.first.wpilibj.Timer

/**
 * A Timer using WPI lib functionality.
 */
public class WPITimer(timer: Timer = Timer()) : ITimer {

    private val mTimer: Timer

    init {
        mTimer = timer
    }

    /**
     * Gets the time since last reset.
     */
    public override fun get() = mTimer.get()

    /**
     * Starts the timer.
     */
    public override fun start() = mTimer.start()

    /**
     * Stops the timer.
     */
    public override fun stop() = mTimer.stop()

    /**
     * Resets the timer's value. Sets the timer to 0.
     */
    public override fun reset() = mTimer.reset()
}
