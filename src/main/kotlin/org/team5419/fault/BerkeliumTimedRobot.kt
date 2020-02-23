package org.team5419.fault

import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.hal.NotifierJNI
import edu.wpi.first.wpilibj.RobotController
import org.team5419.fault.math.units.SIUnit
import org.team5419.fault.math.units.Second
import org.team5419.fault.math.units.milliseconds
import org.team5419.fault.math.units.seconds

open class BerkeliumTimedRobot(
    period: SIUnit<Second> = kDefaultPeriod,
    overrunDumpPeriod: SIUnit<Second> = kDefaultOverrunDumpPeriod
) : BerkeliumIterativeRobotBase(period, overrunDumpPeriod) {

    private val mNotifierHandle = NotifierJNI.initializeNotifier()
    private var mExpirationTime = 0.0

    init {
        NotifierJNI.setNotifierName(mNotifierHandle, "TimedRobot")
        HAL.report(FRCNetComm.tResourceType.kResourceType_Framework, FRCNetComm.tInstances.kFramework_Timed)
        HAL.report(FRCNetComm.tResourceType.kResourceType_Language, kLanguageKotlin)
    }

    override fun startCompetition() {
        robotInit()
        HAL.observeUserProgramStarting()
        mExpirationTime = RobotController.getFPGATime() * 1e-6 + mPeriod.value
        while (true) {
            var currentTime = NotifierJNI.waitForNotifierAlarm(mNotifierHandle)
            if (currentTime == 0.toLong()) {
                // this is something wpilib checks for.
                // no idea when this would actually happen.
                break
            }
            mExpirationTime += mPeriod.value.toLong()
            updateAlarm()
            loopFunc()
        }
    }

    override fun endCompetition() {
        NotifierJNI.stopNotifier(mNotifierHandle)
    }

    fun getPeriod() = mPeriod

    protected fun finalize() {
        NotifierJNI.stopNotifier(mNotifierHandle)
        NotifierJNI.cleanNotifier(mNotifierHandle)
    }

    private fun updateAlarm() {
        NotifierJNI.updateNotifierAlarm(mNotifierHandle, (mExpirationTime * 1e6).toLong())
    }

    companion object {
        private val kDefaultPeriod = 20.milliseconds
        private val kDefaultOverrunDumpPeriod = 5.seconds
        private const val kLanguageKotlin = 6
    }
}
