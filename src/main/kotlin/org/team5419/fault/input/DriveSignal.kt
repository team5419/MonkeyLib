package org.team5419.berkeleyLib.input

import org.team5419.berkeleyLib.util.Source

typealias DriveSignalSource = Source<DriveSignal>

data class DriveSignal(val left: Double = 0.0, val right: Double = 0.0, val brake: Boolean = false) {
    companion object {
        val kBrake = DriveSignal(0.0, 0.0, true)
        val kNeutral = DriveSignal()
    }
}
