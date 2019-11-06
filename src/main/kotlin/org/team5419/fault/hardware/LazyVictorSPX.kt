package org.team5419.fault.hardware

import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.NeutralMode

/**
 * Like a Victor, except it stops you from repeating commands.
 * @param deviceNumber The id of the Victor.
 */
class LazyVictorSPX(deviceNumber: Int) : VictorSPX(deviceNumber) {

    private var lastSet = Double.NaN
    private var lastSecondarySet = Double.NaN
    private var lastControlMode: ControlMode? = null
    private var lastDemandType: DemandType? = null
    private var lastBrakeMode: NeutralMode? = null

    /**
     * Sets the speed of the motor based on the mode and value.
     * @param mode Tells the controller how to interpret the value supplied.
     * @param value The value of the motor based on the mode set.
     */
    public override fun set(mode: ControlMode, value: Double) {
        if (value != lastSet || mode != lastControlMode) {
            lastSet = value
            lastControlMode = mode
            lastSecondarySet = Double.NaN
            lastDemandType = null
            super.set(mode, value)
        }
    }

    /**
     * Sets the speed of the motor based on the modes and values.
     * For further reading: https://tinyurl.com/y4chch5z
     * @param mode Tells the controller how to interpret the values supplied. See further reading.
     * @param value0 The value of the motor based on the mode set.
     * @param demandType Tells the controller how to affect the values supplied. See further reading.
     * @param value1 The value of which the motor is affected based on the demandType.
     */
    public override fun set(mode: ControlMode, value0: Double, demandType: DemandType, value1: Double) {
        @Suppress("ComplexCondition")
        if (value0 != lastSet || mode != lastControlMode ||
            demandType != lastDemandType || value1 != lastSecondarySet) {
            lastSet = value0
            lastControlMode = mode
            lastDemandType = demandType
            lastSecondarySet = value1
            super.set(mode, value0, demandType, value1)
        }
    }

    /**
     * Sets the mode of operation during neutral throttle output.
     * @param brakeMode Tells the controller to either brake or coast.
     */
    public override fun setNeutralMode(brakeMode: NeutralMode) {
        if (lastBrakeMode != brakeMode) {
            lastBrakeMode = brakeMode
            super.setNeutralMode(brakeMode)
        }
    }
}
