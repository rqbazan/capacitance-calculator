package santiago.project.university.currentcalculator.contract

import android.support.v7.widget.AppCompatButton
import android.view.View
import santiago.project.university.currentcalculator.model.entity.UResistance
import santiago.project.university.currentcalculator.model.entity.UVolt

/**
 * Created by cesar on 01/06/17.
 */
interface IMainView {
    fun onClickBtnSolve()
    fun onClickBtnResistanceUnit(button: AppCompatButton)
    fun onClickBtnVoltUnit(button: AppCompatButton)
    fun setupListOfUResistance(units: Array<UResistance>)
    fun setupListOfUVolt(units: Array<UVolt>)
    fun showResult(I1: Double, I2: Double, I3: Double)
    fun checkAllInputsRequired(): Boolean
}