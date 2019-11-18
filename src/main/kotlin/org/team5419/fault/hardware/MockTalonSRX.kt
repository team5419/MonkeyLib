package org.team5419.fault.hardware

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.NeutralMode

public class MockTalonSRX(deviceNumber: Int) : Talon {
    private var mDeviceNumber: Int

    private var mMode: ControlMode? = null
    private var mValue: Double? = null
    private var mDemandType: DemandType? = null
    private var mSecondarySet: Double? = null
    private var mNeutralMode: NeutralMode? = null

    init {
        mDeviceNumber = deviceNumber
    }

    public override fun set(controlMode: ControlMode, value: Double) {
        mMode = controlMode
        mValue = value
    }

    public override fun set(mode: ControlMode, value: Double, demandType: DemandType, value1: Double) {
        mMode = mode
        mValue = value
        mDemandType = demandType
        mSecondarySet = value1
    }

    public override fun setNeutralMode(brakeMode: NeutralMode) {
        mNeutralMode = brakeMode
    }

    public override fun getSensorCollection() {
    }
}
