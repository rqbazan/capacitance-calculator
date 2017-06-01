package santiago.project.university.currentcalculator.model.entity

import android.support.annotation.StringRes
import santiago.project.university.currentcalculator.R
import javax.measure.quantity.ElectricResistance
import javax.measure.unit.SI
import javax.measure.unit.Unit

/**
 * Created by cesar on 01/06/17.
 */
enum class UResistance(@StringRes val symbol: Int, @StringRes val fullname: Int, val unit: Unit<ElectricResistance>) {
    OHMIOS(R.string.symbol_ohmios, R.string.unit_ohmios, SI.OHM),
    MILIOHMIOS(R.string.symbol_miliohmios, R.string.unit_miliohmios, SI.MILLI(SI.OHM)),
    NANOOHMIOS(R.string.symbol_nanoohmios, R.string.unit_nanoohmios, SI.NANO(SI.OHM)),
    KILOOHMIOS(R.string.symbol_kiloohmios, R.string.unit_kiloohmios, SI.KILO(SI.OHM))
}