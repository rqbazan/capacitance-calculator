package santiago.project.university.currentcalculator.model.entity

import org.jscience.physics.amount.Amount
import javax.measure.quantity.ElectricResistance

/**
 * Created by santiago on 01/06/17.
 */
data class Resistance(val value: Amount<ElectricResistance>, val unit: UResistance) {
    constructor(value: Double, unit: UResistance) : this(Amount.valueOf(value, unit.unit), unit)
}