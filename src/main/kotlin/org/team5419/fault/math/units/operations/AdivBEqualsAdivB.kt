package org.team5419.berkeleyLib.math.units.operations

import org.team5419.berkeleyLib.math.units.Frac
import org.team5419.berkeleyLib.math.units.SIKey
import org.team5419.berkeleyLib.math.units.SIUnit

operator fun <A : SIKey, B : SIKey> SIUnit<A>.div(other: SIUnit<B>) = SIUnit<Frac<A, B>>(value.div(other.value))
