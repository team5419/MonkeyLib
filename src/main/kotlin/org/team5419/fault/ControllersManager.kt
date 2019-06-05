package org.team5419.frc2019.controllers

import org.team5419.fault.Controller

@Suppress("TooManyFunctions")
public class ControllersManager(
    teleopController: Controller?,
    autoController: Controller?,
    testController: Controller?
) {
    private val mTeleopController: Controller?
    private val mAutoController: Controller?
    private val mTestController: Controller?

    init {
        mTeleopController = teleopController
        mAutoController = autoController
        mTestController = testController
    }

    // general

    fun resetAll() {
        mTeleopController?.reset()
        mAutoController?.reset()
        mTestController?.reset()
    }

    // robot

    fun robotInit() {
        mTeleopController?.start()
        mAutoController?.start()
        mTestController?.start()
    }

    fun robotPeriodic() {
    }

    // disabled mode

    fun disabledInit() {
        resetAll()
    }

    fun disabledPeriodic() {
    }

    // autonomous mode

    fun autonomousInit() {
        resetAll()

        mAutoController?.start()
    }

    fun autonomousPeriodic() {
        mAutoController?.update()
    }

    // teleop mode

    fun teleopInit() {
        resetAll()

        mTeleopController?.start()
    }

    fun teleopPeriodic() {
        mTeleopController?.update()
    }

    // test mode

    fun testInit() {
        resetAll()

        mTestController?.start()
    }

    fun testPeriodic() {
        mTestController?.update()
    }
}
