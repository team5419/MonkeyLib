package org.team5499.monkeyLib.math

import org.team5499.monkeyLib.util.CSVWritable

/**
 * A mathematical model of a motor
 *
 * This class contains useful functions for predicting the state of a motor.
 * Values for different FRC motors at motors.vexpro.com
 *
 * @property freeSpeed the free speed of the motor at 12 volts in rpm
 * @property freeCurrent the free current of the motor at 12 volts in amps
 * @property maximumPower the maximum power output of the motor at 12 volts in watts
 * @property stallTorque the stall torque at 12 volts in Newton meters
 * @property stallCurrent the stall current at 12 volts in amps
 * @constructor creates a motor model with the specified parameters
 */
class MotorModel(
    val freeSpeed: Double,
    val freeCurrent: Double,
    val maximumPower: Double,
    val stallTorque: Double,
    val stallCurrent: Double
) : CSVWritable {

    val kV: Double
    val kT: Double
    val internalResistance: Double

    init {
        internalResistance = 12.0 / stallCurrent
        kV = freeSpeed / (12.0 - (internalResistance * freeCurrent))
        kT = stallTorque / stallCurrent
    }

    /**
     * Get the theoretical voltage required to spin this motor at the specified speed and torque.
     *
     * @param speed The required speed in RPM
     * @param torque The required torque in N x m
     * @return The voltage required for the motor
     */
    fun getVoltageForSpeedAndTorque(speed: Double, torque: Double): Double {
        return (torque * internalResistance / kT) + (speed / kV)
    }

    override fun toCSV(): String =
        "$freeSpeed,$freeCurrent,$maximumPower,$stallTorque,$stallCurrent,$kV,$kT,$internalResistance"
}
