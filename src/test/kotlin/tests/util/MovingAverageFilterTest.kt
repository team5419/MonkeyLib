package tests.util

import org.junit.Test
import org.team5419.fault.util.MovingAverageFilter

class MovingAverageFilterTest {

    @Test
    fun initialValueTest() {
        val filter = MovingAverageFilter(10)
        assert(filter.average == 0.0)
    }

    @Test
    fun averageTest() {
        val filter = MovingAverageFilter(10)
        filter.addValue(1.0)
        println(filter.average)
        assert(filter.average == 0.1)
        filter.addValue(2.0)
        filter.addValue(3.0)
        filter.addValue(4.0)
        filter.addValue(5.0)
        filter.addValue(6.0)
        filter.addValue(7.0)
        filter.addValue(8.0)
        filter.addValue(9.0)
        filter.addValue(10.0)
        assert(filter.average == 5.5)
        filter.addValue(0.0)
        assert(filter.average == 5.4)
    }
}
