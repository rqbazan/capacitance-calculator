package santiago.project.university.currentcalculator.model.entity

import android.support.annotation.StringRes
import santiago.project.university.currentcalculator.R
import javax.measure.quantity.ElectricResistance
import javax.measure.unit.SI
import javax.measure.unit.Unit

/**
 * Created by santiago on 01/06/17.
 */
enum class UResistance(@StringRes val symbol: Int, @StringRes val fullname: Int, val unit: Unit<ElectricResistance>) {
    KILOOHMIOS(R.string.symbol_kiloohmios, R.string.fullname_kiloohmios, SI.KILO(SI.OHM)),
    OHMIOS(R.string.symbol_ohmios, R.string.fullname_ohmios, SI.OHM),
    MILIOHMIOS(R.string.symbol_miliohmios, R.string.fullname_miliohmios, SI.MILLI(SI.OHM)),
    MICROOHMIOS(R.string.symbol_microohmios, R.string.fullname_microohmios, SI.MICRO(SI.OHM)),
    NANOOHMIOS(R.string.symbol_nanoohmios, R.string.fullname_nanoohmios, SI.NANO(SI.OHM))
}