package org.team5419.berkeleyLib.hardware.ctre

import com.ctre.phoenix.motorcontrol.can.VictorSPX
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.SIKey
import org.team5419.berkeleyLib.math.units.derived.Radian
import org.team5419.berkeleyLib.math.units.native.NativeUnitModel

typealias LinearBerkeleiumSPX = BerkeleySPX<Meter>
typealias AngularBerkeleiumSPX = BerkeleySPX<Radian>

class BerkeleySPX<T : SIKey>(
    val victorSPX: VictorSPX,
    model: NativeUnitModel<T>
) : CTREBerkeleyMotor<T>(victorSPX, model) {

    init {
        victorSPX.configFactoryDefault()
    }

    constructor(id: Int, model: NativeUnitModel<T>): this(VictorSPX(id), model)
}
