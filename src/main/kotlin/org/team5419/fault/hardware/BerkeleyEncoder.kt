package org.team5419.berkeleyLib.hardware

import org.team5419.berkeleyLib.math.units.SIKey
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.Velocity
import org.team5419.berkeleyLib.math.units.native.NativeUnit
import org.team5419.berkeleyLib.math.units.native.NativeUnitVelocity

interface BerkeleyEncoder<T : SIKey> {

    /**
     * The velocity of the encoder in [T]/s
     */
    val velocity: SIUnit<Velocity<T>>
    /**
     * The position of the encoder in [T]
     */
    val position: SIUnit<T>

    /**
     * The velocity of the encoder in NativeUnits/s
     */
    val rawVelocity: SIUnit<NativeUnitVelocity>
    /**
     * The position of the encoder in NativeUnits
     */
    val rawPosition: SIUnit<NativeUnit>

    fun resetPosition(newPosition: SIUnit<T> = SIUnit(0.0))

    fun resetPositionRaw(newPosition: SIUnit<NativeUnit> = SIUnit(0.0))
}
