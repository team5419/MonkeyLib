package tests.hardware

import org.junit.Test

import org.team5419.fault.hardware.Victor
import org.team5419.fault.hardware.Talon

import org.team5419.fault.hardware.MockVictorSPX
import org.team5419.fault.hardware.MockTalonSRX

class MockHardwareTest {
    @Test
    public fun testCallback() {
        var mockTalon: Talon = MockTalonSRX(1)
        var mockVictor: Victor = MockVictorSPX(1)
    }
}
