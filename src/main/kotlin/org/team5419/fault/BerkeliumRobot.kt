package org.team5419.fault

import edu.wpi.first.wpilibj.RobotBase
import org.team5419.fault.math.units.SIUnit
import org.team5419.fault.math.units.Second
import org.team5419.fault.math.units.milliseconds
import org.team5419.fault.math.units.seconds
import org.team5419.fault.subsystems.Subsystem
import org.team5419.fault.subsystems.SubsystemManager

@Suppress("TooManyFunctions")
abstract class BerkeliumRobot(
    period: SIUnit<Second> = SIUnit<Second>(0.05),
    overrunDumpPeriod: SIUnit<Second> = 5.seconds
) {

    protected val wrappedValue = BerkTimedRobot(period, overrunDumpPeriod)

    protected inner class BerkTimedRobot(
        period: SIUnit<Second> = 20.milliseconds,
        overrunDumpPeriod: SIUnit<Second> = 5.seconds
    ) : BerkeliumTimedRobot(period, overrunDumpPeriod) {

        override fun robotInit() {
            this@BerkeliumRobot.robotInit()
            SubsystemManager.init()
        }

        override fun autonomousInit() {
            this@BerkeliumRobot.autonomousInit()
            SubsystemManager.autoReset()
        }

        override fun teleopInit() {
            this@BerkeliumRobot.teleopInit()
            SubsystemManager.teleopReset()
        }

        override fun disabledInit() {
            this@BerkeliumRobot.disabledInit()
            SubsystemManager.zeroOutputs()
        }

        override fun testInit() {
            this@BerkeliumRobot.testInit()
        }

        override fun robotPeriodic() {
            this@BerkeliumRobot.robotPeriodic()
            if (!isDisabled) SubsystemManager.periodic()
        }

        override fun autonomousPeriodic() {
            this@BerkeliumRobot.autonomousPeriodic()
        }

        override fun teleopPeriodic() {
            this@BerkeliumRobot.teleopPeriodic()
        }

        override fun disabledPeriodic() {
            this@BerkeliumRobot.disabledPeriodic()
        }

        override fun testPeriodic() {
            this@BerkeliumRobot.testPeriodic()
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
