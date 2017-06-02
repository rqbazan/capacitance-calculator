package santiago.project.university.currentcalculator.model.interactor

import santiago.project.university.currentcalculator.contract.IMainInteractor
import santiago.project.university.currentcalculator.model.entity.UResistance
import santiago.project.university.currentcalculator.model.entity.UVolt

/**
 * Created by santiago on 01/06/17.
 */
class MainInteractor : IMainInteractor {
    override fun getAllUnitResistance(): Array<UResistance> {
        return UResistance.values()
    }

    override fun getAllUnitVolt(): Array<UVolt> {
        return UVolt.values()
    }
}