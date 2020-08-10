package org.team5419.berkeleyLib

import edu.wpi.first.hal.FRCNetComm
import edu.wpi.first.hal.HAL
import edu.wpi.first.wpilibj.RobotBase
import edu.wpi.first.wpilibj.TimedRobot
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.Second
import org.team5419.berkeleyLib.subsystems.Subsystem
import org.team5419.berkeleyLib.subsystems.SubsystemManager

@Suppress("TooManyFunctions")
abstract class BerkeleyRobot(period: SIUnit<Second> = SIUnit<Second>(0.05)) {

    protected val wrappedValue = WpiTimedRobot(period.value)

    protected inner class WpiTimedRobot(period: Double = 0.05) : TimedRobot(period) {

        private val kLanguageKotlin = 6

        init {
            HAL.report(FRCNetComm.tResourceType.kResourceType_Language, kLanguageKotlin)
        }

        override fun robotInit() {
            this@BerkeleyRobot.robotInit()
            SubsystemManager.init()
        }

        override fun autonomousInit() {
            SubsystemManager.autoReset()
            this@BerkeleyRobot.autonomousInit()
        }

        override fun teleopInit() {
            SubsystemManager.teleopReset()
            this@BerkeleyRobot.teleopInit()
        }

        override fun disabledInit() {
            SubsystemManager.zeroOutputs()
            this@BerkeleyRobot.disabledInit()
        }

        override fun testInit() {
            this@BerkeleyRobot.testInit()
        }

        override fun robotPeriodic() {
            this@BerkeleyRobot.robotPeriodic()
            if (!isDisabled) SubsystemManager.periodic()
        }

        override fun autonomousPeriodic() {
            this@BerkeleyRobot.autonomousPeriodic()
        }

        override fun teleopPeriodic() {
            this@BerkeleyRobot.teleopPeriodic()
        }

        override fun disabledPeriodic() {
            this@BerkeleyRobot.disabledPeriodic()
        }

        override fun testPeriodic() {
            this@BerkeleyRobot.testPeriodic()
        }
    }

    protected open fun robotInit() {}
    protected open fun autonomousInit() {}
    protected open fun teleopInit() {}
    protected open fun disabledInit() {}
    protected open fun testInit() {}

    protected open fun robotPeriodic() {}
    protected open fun autonomousPeriodic() {}
    protected open fun teleopPeriodic() {}
    protected open fun disabledPeriodic() {}
    protected open fun testPeriodic() {}

    open operator fun Subsystem.unaryPlus() {
        SubsystemManager.addSubsystem(this)
    }

    fun start() {
        RobotBase.startRobot { wrappedValue }
    }
}
