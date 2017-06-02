package santiago.project.university.currentcalculator.model.entity

import javax.measure.unit.SI

/**
 * Created by santiago on 01/06/17.
 */
class Converter {
    fun toOhms(resistance: Resistance): Resistance {
        return Resistance(resistance.value.to(SI.OHM), UResistance.OHMIOS)
    }

    fun toVolts(volts: Volt): Volt {
        return Volt(volts.value.to(SI.VOLT), UVolt.VOLT)
    }
}