package santiago.project.university.currentcalculator.model.entity

import android.support.annotation.StringRes
import santiago.project.university.currentcalculator.R
import javax.measure.quantity.ElectricPotential
import javax.measure.unit.SI
import javax.measure.unit.Unit

/**
 * Created by cesar on 01/06/17.
 */
enum class UVolt(@StringRes val symbol: Int, @StringRes val fullname: Int, val unit: Unit<ElectricPotential>) {
    VOLT(R.string.symbol_voltios, R.string.unit_voltios, SI.VOLT)
}