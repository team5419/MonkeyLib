package org.team5419.berkeleyLib.hardware

import org.team5419.berkeleyLib.math.units.SIKey

abstract class AbstractBerkeleyMotor<T : SIKey> : BerkeleyMotor<T> {

    override var useMotionProfileForPosition = false

    override fun follow(motor: BerkeleyMotor<*>): Boolean {
        println("Cross brand following not implemented yet!")
        return false
    }
}
