package org.team5419.fault.hardware

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.NeutralMode

public class MockLazyVictorSPX(deviceNumber: Int) : Victor {

    private var mMode: ControlMode? = null
    private var mValue: Double? = null
    private var mDemandType: DemandType? = null
    private var mSecondarySet: Double? = null
    private var mNeutralMode: NeutralMode? = null

    public override fun set(mode: ControlMode, value: Double) {
        mMode = mode
        mValue = value
    }

    public fun set(mode: ControlMode, value: Double, demandType: DemandType, value1: Double) {
        mMode = mode
        mValue = value
        mDemandType = demandType
        mSecondarySet = value1
    }

    public fun setNeutralMode(brakeMode: NeutralMode) {
        mNeutralMode = brakeMode
    }
}
