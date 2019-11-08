/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * Copyright 2019, Green Hope Falcons
 */

package org.ghrobotics.lib.mathematics.units.operations

import org.team5419.fault.math.units.Frac
import org.team5419.fault.math.units.SIKey
import org.team5419.fault.math.units.SIUnit
import org.team5419.fault.math.units.Unitless


operator fun <A : SIKey, B : SIKey> SIUnit<A>.div(other: SIUnit<B>) = SIUnit<Frac<A, B>>(value.div(other.value))
