package santiago.project.university.currentcalculator.presenter

import org.la4j.Vector
import santiago.project.university.currentcalculator.contract.IMainInteractor
import santiago.project.university.currentcalculator.contract.IMainPresenter
import santiago.project.university.currentcalculator.contract.IMainView
import santiago.project.university.currentcalculator.model.entity.Resistance
import santiago.project.university.currentcalculator.model.entity.Solver
import santiago.project.university.currentcalculator.model.entity.Volt
import santiago.project.university.currentcalculator.model.interactor.MainInteractor

/**
 * Created by santiago on 01/06/17.
 */
class MainPresenter(val view: IMainView, val model: IMainInteractor = MainInteractor()) : IMainPresenter {
    val solver = Solver()

    override fun loadListOfUnits() {
        view.setupListOfUResistance(model.getAllUnitResistance())
        view.setupListOfUVolt(model.getAllUnitVolt())
    }

    override fun solve(v8: Volt, v9: Volt, v10: Volt, vararg resistances: Resistance) {
        val currents = solver.solve(v8, v9, v10, resistances)
        view.showResult(currents[0], currents[1], currents[2])
    }
}