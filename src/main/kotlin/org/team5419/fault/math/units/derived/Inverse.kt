package org.team5419.berkeleyLib.math.units.derived

import org.team5419.berkeleyLib.math.units.Frac
import org.team5419.berkeleyLib.math.units.Meter
import org.team5419.berkeleyLib.math.units.Unitless

typealias Inverse<T> = Frac<Unitless, T>
typealias Curvature = Inverse<Meter>
