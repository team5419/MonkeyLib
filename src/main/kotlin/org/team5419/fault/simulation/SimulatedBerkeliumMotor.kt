package org.team5419.berkeleyLib.simulation

import org.team5419.berkeleyLib.hardware.BerkeleyEncoder
import org.team5419.berkeleyLib.hardware.BerkeleyMotor
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.SIKey
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.Acceleration
import org.team5419.berkeleyLib.math.units.derived.Radian
import org.team5419.berkeleyLib.math.units.derived.Velocity
import org.team5419.berkeleyLib.math.units.derived.Volt
import org.team5419.berkeleyLib.math.units.native.NativeUnit
import org.team5419.berkeleyLib.math.units.native.NativeUnitVelocity

typealias LinearSimulatedBerkeleiumMotor = SimulatedBerkeleyMotor<Meter>
typealias AngularSimulatedBerkeleiumMotor = SimulatedBerkeleyMotor<Radian>

class SimulatedBerkeleyMotor<T : SIKey> : BerkeleyMotor<T> {

    var velocity = SIUnit<Velocity<T>>(0.0)
    override val voltageOutput = SIUnit<Volt>(0.0)

    override val encoder = object : BerkeleyEncoder<T> {
        override val position: SIUnit<T> get() = SIUnit(0.0)
        override val velocity: SIUnit<Velocity<T>> get() = this@SimulatedBerkeleyMotor.velocity
        override val rawPosition: SIUnit<NativeUnit> get() = SIUnit(position.value)
        override val rawVelocity: SIUnit<NativeUnitVelocity> get() = SIUnit(velocity.value)

        override fun resetPosition(newPosition: SIUnit<T>) {}
        override fun resetPositionRaw(newPosition: SIUnit<NativeUnit>) {}
    }

    override var outputInverted: Boolean
        get() = TODO("not implemented")
        set(value) {}

    override var brakeMode: Boolean
        get() = TODO("not implemented")
        set(value) {}

    override fun follow(motor: BerkeleyMotor<*>): Boolean {
        TODO("not implemented")
    }

    override fun setVoltage(voltage: SIUnit<Volt>, arbitraryFeedForward: SIUnit<Volt>) {
        TODO("not implemented")
    }

    override fun setPercent(percent: Double, arbitraryFeedForward: SIUnit<Volt>) {
        TODO("not implemented")
    }

    override fun setVelocity(velocity: SIUnit<Velocity<T>>, arbitraryFeedForward: SIUnit<Volt>) {
        this.velocity = velocity
    }

    override fun setPosition(position: SIUnit<T>, arbitraryFeedForward: SIUnit<Volt>) {
        TODO("not implemented")
    }

    override fun setNeutral() {
        velocity = SIUnit(0.0)
    }

    override var voltageCompSaturation: SIUnit<Volt>
        get() = TODO("not implemented")
        set(value) {}

    override var motionProfileCruiseVelocity: SIUnit<Velocity<T>>
        get() = TODO("not implemented")
        set(value) {}

    override var motionProfileAcceleration: SIUnit<Acceleration<T>>
        get() = TODO("not implemented")
        set(value) {}

    override var useMotionProfileForPosition: Boolean
        get() = TODO("not implemented")
        set(value) {}
}
