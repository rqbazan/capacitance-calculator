package santiago.project.university.currentcalculator.model.entity

import org.jscience.physics.amount.Amount
import javax.measure.quantity.ElectricPotential

/**
 * Created by santiago on 01/06/17.
 */
data class Volt(val value: Amount<ElectricPotential>, val unit: UVolt) {
    constructor(value: Double, unit: UVolt) : this(Amount.valueOf(value, unit.unit), unit)
}