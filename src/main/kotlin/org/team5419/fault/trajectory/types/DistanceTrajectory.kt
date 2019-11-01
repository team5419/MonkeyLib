package org.team5419.fault.trajectory.types

import org.team5419.fault.math.epsilonEquals
import org.team5419.fault.math.geometry.State
import org.team5419.fault.trajectory.TrajectoryIterator
import org.team5419.fault.math.units.Length
import org.team5419.fault.math.units.meter

// distance and state
class DistanceTrajectory<S : State<S>>(
    override val points: List<S>
) : Trajectory<Length, S> {

    private val distances: List<Double>

    init {
        val tempDistances = mutableListOf<Double>()
        tempDistances += 0.0
        points.zipWithNext {
            c, n -> tempDistances += tempDistances.last() + c.distance(n)
        }
        distances = tempDistances
    }

    override fun sample(interpolant: Length) = sample(interpolant.value)

    fun sample(interpolant: Double) = when {
        interpolant >= lastInterpolant.value -> TrajectorySamplePoint(getPoint(points.size - 1))
        interpolant <= 0.0 -> TrajectorySamplePoint(getPoint(0))
        else -> {
            val (index, entry) = points.asSequence()
                .withIndex()
                .first { (index, _) -> index != 0 && distances[index] >= interpolant }
            val prevEntry = points[index - 1]
            if (distances[index] epsilonEquals distances[index - 1]) {
                TrajectorySamplePoint(entry, index, index)
            } else {
                TrajectorySamplePoint(
                    prevEntry.interpolate(
                        entry,
                        (interpolant - distances[index - 1]) / (distances[index] - distances[index - 1])
                    ),
                    index - 1,
                    index
                )
            }
        }
    }

    override val firstState get() = points.first()
    override val lastState get() = points.last()

    override val firstInterpolant get() = 0.0.meter
    override val lastInterpolant get() = distances.last().toDouble().meter

    override fun iterator() = DistanceIterator(this)
}

class DistanceIterator<S : State<S>>(
    trajectory: DistanceTrajectory<S>
) : TrajectoryIterator<Length, S>(trajectory) {
    override fun addition(a: Length, b: Length) = a + b
}
