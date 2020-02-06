package org.team5419.fault.hardware.ctre

import com.ctre.phoenix.motorcontrol.can.TalonFX
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice
import org.team5419.fault.math.units.SIUnit
import org.team5419.fault.math.units.SIKey
import org.team5419.fault.math.units.Meter
import org.team5419.fault.math.units.Second
import org.team5419.fault.math.units.inMilliseconds
import org.team5419.fault.math.units.Ampere
import org.team5419.fault.math.units.inAmps
import org.team5419.fault.math.units.derived.Radian
import org.team5419.fault.math.units.native.NativeUnitModel

typealias LinearBerkeleiumFX = BerkeliumFX<Meter>
typealias AngularBerkeleiumFX = BerkeliumFX<Radian>

class BerkeliumFX<T : SIKey>(
    val talonFX: TalonFX,
    model: NativeUnitModel<T>
) : CTREBerkeliumMotor<T>(talonFX, model) {

    constructor(id: Int, model: NativeUnitModel<T>): this(TalonFX(id), model)

    init {
        talonFX.configFactoryDefault(0)
        talonFX.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor)
    }

    fun configCurrentLimit(enabled: Boolean, config: CurrentLimitConfig? = null) {
        talonFX.enableCurrentLimit(enabled)
        if (enabled && config != null) {
            talonFX.configContinuousCurrentLimit(config.continuousCurrentLimit.inAmps().toInt())
            talonFX.configPeakCurrentLimit(config.peakCurrentLimit.inAmps().toInt())
            talonFX.configPeakCurrentDuration(config.peakCurrentLimitDuration.inMilliseconds().toInt())
        }
    }

    data class CurrentLimitConfig(
        val peakCurrentLimit: SIUnit<Ampere>,
        val peakCurrentLimitDuration: SIUnit<Second>,
        val continuousCurrentLimit: SIUnit<Ampere>
    )
}
