package org.team5419.fault.hardware

import com.ctre.phoenix.motorcontrol.ControlMode

public interface Talon {
    public fun set(controlMode: ControlMode, value: Double)
}
