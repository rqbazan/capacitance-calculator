package santiago.project.university.currentcalculator.contract

import santiago.project.university.currentcalculator.model.entity.UResistance
import santiago.project.university.currentcalculator.model.entity.UVolt

/**
 * Created by cesar on 01/06/17.
 */
interface IMainInteractor {
    fun getAllUnitResistance(): Array<UResistance>
    fun getAllUnitVolt(): Array<UVolt>
}