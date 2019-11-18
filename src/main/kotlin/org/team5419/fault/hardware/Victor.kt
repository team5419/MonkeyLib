package org.team5419.fault.hardware

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.NeutralMode

public interface Victor {
    public fun set(controlMode: ControlMode, value: Double)

    public fun set(mode: ControlMode, value: Double, demandType: DemandType, value1: Double)

    public fun setNeutralMode(brakeMode: NeutralMode)
}
