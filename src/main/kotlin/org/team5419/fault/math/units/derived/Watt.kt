package org.team5419.berkeleyLib.math.units.derived

import org.team5419.berkeleyLib.math.units.Frac
import org.team5419.berkeleyLib.math.units.Mult
import org.team5419.berkeleyLib.math.units.Kilogram
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.Second
import org.team5419.berkeleyLib.math.units.SIUnit

typealias Watt = Frac<Mult<Kilogram, Mult<Meter, Meter>>, Mult<Second, Mult<Second, Second>>>

val Double.watts get() = SIUnit<Watt>(this)
val Number.watts get() = toDouble().watts
