package org.team5419.berkeleyLib.hardware.ctre

import com.ctre.phoenix.sensors.PigeonIMU
import org.team5419.berkeleyLib.math.geometry.Rotation2d
import org.team5419.berkeleyLib.math.units.derived.degrees
import org.team5419.berkeleyLib.util.Source

fun PigeonIMU.asSource(): Source<Rotation2d> = { Rotation2d(fusedHeading.degrees) }
