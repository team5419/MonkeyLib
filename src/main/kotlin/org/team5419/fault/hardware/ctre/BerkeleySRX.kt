package org.team5419.berkeleyLib.hardware.ctre

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import org.team5419.berkeleyLib.math.units.SIUnit
import org.team5419.berkeleyLib.math.units.SIKey
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.Second
import org.team5419.berkeleyLib.math.units.inMilliseconds
import org.team5419.berkeleyLib.math.units.Ampere
import org.team5419.berkeleyLib.math.units.inAmps
import org.team5419.berkeleyLib.math.units.derived.Radian
import org.team5419.berkeleyLib.math.units.native.NativeUnitModel

typealias LinearBerkeleiumSRX = BerkeleySRX<Meter>
typealias AngularBerkeleiumSRX = BerkeleySRX<Radian>

class BerkeleySRX<T : SIKey>(
    val talonSRX: TalonSRX,
    model: NativeUnitModel<T>
) : CTREBerkeleyMotor<T>(talonSRX, model) {

    constructor(id: Int, model: NativeUnitModel<T>): this(TalonSRX(id), model)

    init {
        talonSRX.configFactoryDefault(0)
    }

    fun configCurrentLimit(enabled: Boolean, config: CurrentLimitConfig? = null) {
        talonSRX.enableCurrentLimit(enabled)
        if (enabled && config != null) {
            talonSRX.configContinuousCurrentLimit(config.continuousCurrentLimit.inAmps().toInt())
            talonSRX.configPeakCurrentLimit(config.peakCurrentLimit.inAmps().toInt())
            talonSRX.configPeakCurrentDuration(config.peakCurrentLimitDuration.inMilliseconds().toInt())
        }
    }

    data class CurrentLimitConfig(
        val peakCurrentLimit: SIUnit<Ampere>,
        val peakCurrentLimitDuration: SIUnit<Second>,
        val continuousCurrentLimit: SIUnit<Ampere>
    )
}
