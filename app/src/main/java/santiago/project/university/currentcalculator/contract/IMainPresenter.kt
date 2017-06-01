package santiago.project.university.currentcalculator.contract

import org.la4j.Vector
import santiago.project.university.currentcalculator.model.entity.Resistance
import santiago.project.university.currentcalculator.model.entity.Volt

/**
 * Created by cesar on 01/06/17.
 */
interface IMainPresenter {
    fun loadListOfUnits()
    fun solve(v8: Volt, v9: Volt, v10: Volt, vararg resistances: Resistance)
}