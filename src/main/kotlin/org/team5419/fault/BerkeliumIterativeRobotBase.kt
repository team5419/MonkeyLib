package org.team5419.fault

import edu.wpi.first.hal.HAL
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.Timer
import org.team5419.fault.math.units.SIUnit
import org.team5419.fault.math.units.Second
import org.team5419.fault.math.units.seconds

@Suppress("TooManyFunctions")
abstract class BerkeliumIterativeRobotBase(
    protected var mPeriod: SIUnit<Second>,
    protected var mOverrunPrintPeriod: SIUnit<Second>
) : RobotBase() {

    enum class Mode {
        None,
        Disabled,
        Autonomous,
        Teleop,
        Test
    }

    private var mLastMode = Mode.None

    private var mLoopStartTime = 0.0.seconds
    private var mLoopEndTime = 0.0.seconds

    private var mLastOverrunDump = 0.0.seconds
    private var mOverrunCount = 0

    abstract override fun startCompetition()

    open fun robotInit() = println("Default robot init method... Override me")
    open fun disabledInit() = println("Default disabled init method... Override me")
    open fun teleopInit() = println("Default teleop init method... Override me")
    open fun autonomousInit() = println("Default auto init method... Override me")
    open fun testInit() = println("Default test init method... Override me")

    // variables to prevent the console being spammed
    // with overide messages. We only need to hear about it once, and
    // print statements are most likely slow anyways
    private var mRobotFirstLoop = true
    private var mDisabledFirstLoop = true
    private var mTeleopFirstLoop = true
    private var mAutoFirstLoop = true
    private var mTestFirstLoop = true

    open fun robotPeriodic() {
        if (mRobotFirstLoop) {
            println("Default robot periodic method... Override me")
            mRobotFirstLoop = false
        }
    }

    open fun disabledPeriodic() {
        if (mDisabledFirstLoop) {
            println("Default disabled periodic method... Override me")
            mDisabledFirstLoop = false
        }
    }

    open fun teleopPeriodic() {
        if (mTeleopFirstLoop) {
            println("Default teleop periodic method... Override me")
            mTeleopFirstLoop = false
        }
    }

    open fun autonomousPeriodic() {
        if (mAutoFirstLoop) {
            println("Default autonomous periodic method... Override me")
            mAutoFirstLoop = false
        }
    }

    open fun testPeriodic() {
        if (mTestFirstLoop) {
            println("Default test periodic method... Override me")
            mTestFirstLoop = false
        }
    }

    @Suppress("ComplexMethod")
    protected fun loopFunc() {
        mLoopStartTime = Timer.getFPGATimestamp().seconds
        if (isDisabled) {
            if (mLastMode != Mode.Disabled) {
//                Shuffleboard.disableActuatorWidgets()
                disabledInit()
                mLastMode = Mode.Disabled
            }
            HAL.observeUserProgramDisabled()
            disabledPeriodic()
        } else if (isAutonomous) {
            if (mLastMode != Mode.Autonomous) {
//              Shuffleboard.disableActuatorWidgets()
                autonomousInit()
                mLastMode = Mode.Autonomous
            }
            HAL.observeUserProgramAutonomous()
            autonomousPeriodic()
        } else if (isOperatorControl) {
            if (mLastMode != Mode.Teleop) {
                teleopInit()
                mLastMode = Mode.Teleop
            }
            HAL.observeUserProgramTeleop()
            teleopPeriodic()
        } else {
            if (mLastMode != Mode.Test) {
                testInit()
                mLastMode = Mode.Test
            }
            HAL.observeUserProgramTest()
            testPeriodic()
        }
        robotPeriodic()
        mLoopEndTime = Timer.getFPGATimestamp().seconds

        // if the duration of the loop exceeds the desired loop period,
        // this is called an "overrun," they are usually indicators of
        // inefficient code or other issues.
        if (mLoopEndTime > (mPeriod + mLoopStartTime)) {
            mOverrunCount += 1
        }

        // after a certain ammount of time, we want to report the overruns
        // that have occured over the past x ammount of time
        // here we check if it is time to do so
        // this is mainly to prevent spamming the console,
        // as thats inefficient and annoying
        if ((mLoopEndTime - mLastOverrunDump) > mOverrunPrintPeriod) {

            // if we've had atleast 1 overrun in the past x seconds,
            // report it to the console and reset the overrun counter.
            if (mOverrunCount > 0) {
                println("WARNING: There have been $mOverrunCount loop overruns in the " +
                        "past ${mLoopEndTime - mLastOverrunDump} seconds!")

                mOverrunCount = 0
            }
            mLastOverrunDump = mLoopEndTime
        }
    }
}
