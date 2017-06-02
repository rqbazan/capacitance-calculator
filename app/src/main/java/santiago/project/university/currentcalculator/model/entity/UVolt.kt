package santiago.project.university.currentcalculator.model.entity

import android.support.annotation.StringRes
import santiago.project.university.currentcalculator.R
import javax.measure.quantity.ElectricPotential
import javax.measure.unit.SI
import javax.measure.unit.Unit

/**
 * Created by santiago on 01/06/17.
 */
enum class UVolt(@StringRes val symbol: Int, @StringRes val fullname: Int, val unit: Unit<ElectricPotential>) {
    KILOVOLT(R.string.symbol_kilovoltios, R.string.fullname_kilovoltios, SI.KILO(SI.VOLT)),
    VOLT(R.string.symbol_voltios, R.string.fullname_voltios, SI.VOLT),
    MILIVOLT(R.string.symbol_milivoltios, R.string.fullname_milivoltios, SI.MILLI(SI.VOLT)),
    MICROVOLT(R.string.symbol_microvoltios, R.string.fullname_microvoltios, SI.MICRO(SI.VOLT)),
    NANOVOLT(R.string.symbol_nanovoltios, R.string.fullname_nanovoltios, SI.NANO(SI.VOLT))
}