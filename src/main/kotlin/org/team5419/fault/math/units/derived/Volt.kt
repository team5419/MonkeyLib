package org.team5419.berkeleyLib.math.units.derived

import org.team5419.berkeleyLib.math.units.Frac
import org.team5419.berkeleyLib.math.units.Mult
import org.team5419.berkeleyLib.math.units.Kilogram
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.Second
import org.team5419.berkeleyLib.math.units.Ampere
import org.team5419.berkeleyLib.math.units.SIUnit

typealias Volt = Frac<Mult<Kilogram, Mult<Meter, Meter>>,
        Mult<Ampere, Mult<Second, Mult<Second, Second>>>>

val Double.volts get() = SIUnit<Volt>(this)

val Number.volts get() = toDouble().volts
