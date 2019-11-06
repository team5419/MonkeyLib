package org.team5419.fault.hardware

import edu.wpi.first.wpilibj.XboxController

import org.team5419.fault.util.time.ITimer
import org.team5419.fault.util.time.WPITimer

import java.util.concurrent.atomic.AtomicBoolean

/**
 * An Xbox Controller, except with rumble capabilities.
 * @param portNumber The port that the XBox Controller is connected to.
 * @param timer
 */
// adapted from 1323
public class XboxControllerPlus(portNumber: Int, timer: ITimer = WPITimer()) : XboxController(portNumber) {

    private val mRumbling: AtomicBoolean
    private val mTimer: ITimer

    public val isRumbling: Boolean
        get() = mRumbling.get()

    init {
        mRumbling = AtomicBoolean(false)
        mTimer = timer
    }

    /**
     * Stops the rumbling of the controller.
     */
    public fun cancelRumble() {
        mRumbling.set(false)
    }

    /**
     * Makes the controller rumble.
     * @param rumblesPerSecond Sets how many times the contoller will rumble a second.
     * @param numberofSeconds Sets how long the rumbling will last.
     */
    public fun rumble(rumblesPerSecond: Double, numberOfSeconds: Double) {
        if (!mRumbling.get()) {
            val thread = RumbleThread(rumblesPerSecond, numberOfSeconds)
            thread.start()
        }
    }

    /**
     * Creates a thread for rumbling on the controller, and controls how long the rumble is.
     * @param rumblesPerSecond Sets how many times the contoller will rumble a second.
     * @param numberofSeconds Sets how long the rumbling will last.
     */
    private inner class RumbleThread(rumblesPerSecond: Double, numberOfSeconds: Double) : Thread() {
        private val mRumblesPerSecond: Double
        private val mInterval: Long
        private val mSeconds: Double

        init {
            mRumblesPerSecond = rumblesPerSecond
            mSeconds = numberOfSeconds
            mInterval = (1 / (rumblesPerSecond * 2.0) * 1000.0).toLong()
        }

        /**
         * Starts the rumbling thread on the controller.
         */
        public override fun start() {
            mRumbling.set(true)
            mTimer.stop()
            mTimer.reset()
            mTimer.start()
            try {
                while (mTimer.get() < mSeconds && mRumbling.get()) {
                    setRumble(RumbleType.kLeftRumble, 1.0)
                    setRumble(RumbleType.kRightRumble, 1.0)
                    sleep(mInterval)
                    setRumble(RumbleType.kLeftRumble, 0.0)
                    setRumble(RumbleType.kRightRumble, 0.0)
                    sleep(mInterval)
                }
            } catch (e: InterruptedException) {
                mRumbling.set(false)
                e.printStackTrace()
            }
            mRumbling.set(false)
        }
    }
}
