package org.team5419.berkeleyLib.hardware

import org.team5419.berkeleyLib.math.units.SIKey
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.derived.Velocity
import org.team5419.berkeleyLib.math.units.native.NativeUnitModel

abstract class AbstractBerkeleyEncoder<T : SIKey>(
    val model: NativeUnitModel<T>
) : BerkeleyEncoder<T> {
    override val position: SIUnit<T> get() = model.fromNativeUnitPosition(rawPosition)
    override val velocity: SIUnit<Velocity<T>> get() = model.fromNativeUnitVelocity(rawVelocity)
}
