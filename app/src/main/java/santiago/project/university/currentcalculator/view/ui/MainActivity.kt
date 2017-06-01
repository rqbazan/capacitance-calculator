package santiago.project.university.currentcalculator.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import santiago.project.university.currentcalculator.R
import santiago.project.university.currentcalculator.contract.IMainView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import santiago.project.university.currentcalculator.contract.IMainPresenter
import santiago.project.university.currentcalculator.model.entity.Resistance
import santiago.project.university.currentcalculator.model.entity.UResistance
import santiago.project.university.currentcalculator.model.entity.UVolt
import santiago.project.university.currentcalculator.model.entity.Volt
import santiago.project.university.currentcalculator.presenter.MainPresenter


class MainActivity : AppCompatActivity(), IMainView {
    val presenter: IMainPresenter by lazy { MainPresenter(this) }
    var uResistanceAsPretty: List<String>? = null
    var uResistances: Array<UResistance>? = null
    var uVoltAsPretty: List<String>? = null
    var uVolts: Array<UVolt>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtons()
        presenter.loadListOfUnits()
    }

    override fun onClickBtnSolve() {
        if (testInputs()) {
            try {
                val r1 = getResistanceValue(edt_value_r1, btn_unit_r1)
                val r2 = getResistanceValue(edt_value_r2, btn_unit_r2)
                val r3 = getResistanceValue(edt_value_r3, btn_unit_r3)
                val r4 = getResistanceValue(edt_value_r4, btn_unit_r4)
                val r5 = getResistanceValue(edt_value_r5, btn_unit_r5)
                val r6 = getResistanceValue(edt_value_r6, btn_unit_r6)
                val r7 = getResistanceValue(edt_value_r7, btn_unit_r7)
                val v8 = getVoltValue(edt_value_v8, btn_unit_v8)
                val v9 = getVoltValue(edt_value_v9, btn_unit_v9)
                val v10 = getVoltValue(edt_value_v10, btn_unit_v10)
                presenter.solve(v8, v9, v10, r1, r2, r3, r4, r5, r6, r7)
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onClickBtnResistanceUnit(button: AppCompatButton) {
        if (uResistanceAsPretty != null) {
            MaterialDialog.Builder(this)
                    .title("Unidades de resistencia")
                    .items(uResistanceAsPretty!!)
                    .itemsCallback({ _, _, _, text ->
                        val unit = uResistances!![uResistanceAsPretty!!.indexOf(text)]
                        button.tag = unit
                        button.text = getString(unit.symbol)
                    }).show()
        }
    }

    override fun onClickBtnVoltUnit(button: AppCompatButton) {
        if (uVoltAsPretty != null) {
            MaterialDialog.Builder(this)
                    .title("Unidades de voltaje")
                    .items(uVoltAsPretty!!)
                    .itemsCallback({ _, _, _, text ->
                        val unit = uVolts!![uVoltAsPretty!!.indexOf(text)]
                        button.tag = unit
                        button.text = getString(unit.symbol)
                    }).show()
        }
    }

    fun setupButtons() {
        btn_unit_r1.tag = UResistance.OHMIOS
        btn_unit_r2.tag = UResistance.OHMIOS
        btn_unit_r3.tag = UResistance.OHMIOS
        btn_unit_r4.tag = UResistance.OHMIOS
        btn_unit_r5.tag = UResistance.OHMIOS
        btn_unit_r6.tag = UResistance.OHMIOS
        btn_unit_r7.tag = UResistance.OHMIOS
        btn_unit_v8.tag = UVolt.VOLT
        btn_unit_v9.tag = UVolt.VOLT
        btn_unit_v10.tag = UVolt.VOLT
        btn_unit_r1.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r1) }
        btn_unit_r2.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r2) }
        btn_unit_r3.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r3) }
        btn_unit_r4.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r4) }
        btn_unit_r5.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r5) }
        btn_unit_r6.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r6) }
        btn_unit_r7.setOnClickListener { onClickBtnResistanceUnit(btn_unit_r7) }
        btn_unit_v8.setOnClickListener { onClickBtnVoltUnit(btn_unit_v8) }
        btn_unit_v9.setOnClickListener { onClickBtnVoltUnit(btn_unit_v9) }
        btn_unit_v10.setOnClickListener { onClickBtnVoltUnit(btn_unit_v10) }
        fab_solve.setOnClickListener { onClickBtnSolve() }
    }

    override fun setupListOfUResistance(units: Array<UResistance>) {
        uResistanceAsPretty = units.map {
            "${getString(it.fullname)} (${getString(it.symbol)})"
        }
        uResistances = units
    }

    override fun setupListOfUVolt(units: Array<UVolt>) {
        uVoltAsPretty = units.map {
            "${getString(it.fullname)} (${getString(it.symbol)})"
        }
        uVolts = units
    }

    fun getResistanceValue(text: AppCompatEditText, symbol: AppCompatButton): Resistance {
        val value = text.text.toString().toDouble()
        val unit: UResistance = symbol.tag as UResistance
        return Resistance(value, unit)
    }

    fun getVoltValue(text: AppCompatEditText, symbol: AppCompatButton): Volt {
        val value = text.text.toString().toDouble()
        val unit: UVolt = symbol.tag as UVolt
        return Volt(value, unit)
    }

    fun testInputs(): Boolean {
        if (edt_value_r1.text.isEmpty()) {
            edt_value_r1.error = "Campo requerido"
            return false
        }
        if (edt_value_r2.text.isEmpty()) {
            edt_value_r2.error = "Campo requerido"
            return false
        }
        if (edt_value_r3.text.isEmpty()) {
            edt_value_r3.error = "Campo requerido"
            return false
        }
        if (edt_value_r4.text.isEmpty()) {
            edt_value_r4.error = "Campo requerido"
            return false
        }
        if (edt_value_r5.text.isEmpty()) {
            edt_value_r5.error = "Campo requerido"
            return false
        }
        if (edt_value_r6.text.isEmpty()) {
            edt_value_r6.error = "Campo requerido"
            return false
        }
        if (edt_value_r7.text.isEmpty()) {
            edt_value_r7.error = "Campo requerido"
            return false
        }
        if (edt_value_v8.text.isEmpty()) {
            edt_value_v8.error = "Campo requerido"
            return false
        }
        if (edt_value_v9.text.isEmpty()) {
            edt_value_v9.error = "Campo requerido"
            return false
        }
        if (edt_value_v10.text.isEmpty()) {
            edt_value_v10.error = "Campo requerido"
            return false
        }
        return true
    }

    override fun showResult(I1: Double, I2: Double, I3: Double) {
        val dialog = MaterialDialog.Builder(this)
                .title("Resultado")
                .positiveText("Ok")
                .customView(R.layout.layout_result, false)
                .build()
        dialog.customView?.find<AppCompatTextView>(R.id.txt_i1)?.text = "I1 = ${I1}A"
        dialog.customView?.find<AppCompatTextView>(R.id.txt_i2)?.text = "I2 = ${I2}A"
        dialog.customView?.find<AppCompatTextView>(R.id.txt_i3)?.text = "I3 = ${I3}A"
        dialog.show()
    }
}
