package santiago.project.university.currentcalculator.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
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

    var edts: List<AppCompatEditText>? = null
    var btnsResistances: List<AppCompatButton>? = null
    var btnsVolts: List<AppCompatButton>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtons()
        presenter.loadListOfUnits()
    }

    override fun onClickBtnSolve() {
        if (checkAllInputsRequired()) {
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
                    .title(R.string.title_main_resistances_choose_dialog)
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
                    .title(R.string.title_main_volts_choose_dialog)
                    .items(uVoltAsPretty!!)
                    .itemsCallback({ _, _, _, text ->
                        val unit = uVolts!![uVoltAsPretty!!.indexOf(text)]
                        button.tag = unit
                        button.text = getString(unit.symbol)
                    }).show()
        }
    }

    fun setupButtons() {
        edts = listOf<AppCompatEditText>(
                edt_value_r1,
                edt_value_r2,
                edt_value_r3,
                edt_value_r4,
                edt_value_r5,
                edt_value_r6,
                edt_value_r7,
                edt_value_v8,
                edt_value_v9,
                edt_value_v10
        )

        btnsResistances = listOf<AppCompatButton>(
                btn_unit_r1,
                btn_unit_r2,
                btn_unit_r3,
                btn_unit_r4,
                btn_unit_r5,
                btn_unit_r6,
                btn_unit_r7
        )

        btnsVolts = listOf<AppCompatButton>(
                btn_unit_v8,
                btn_unit_v9,
                btn_unit_v10
        )

        btnsResistances?.forEach {
            it.tag = UResistance.OHMIOS
            it.setOnClickListener { onClickBtnResistanceUnit(it as AppCompatButton) }
        }

        btnsVolts?.forEach {
            it.tag = UVolt.VOLT
            it.setOnClickListener { onClickBtnVoltUnit(it as AppCompatButton) }
        }

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

    override fun checkAllInputsRequired(): Boolean {
        return edts?.all {
            if (it.text.isEmpty()) {
                it.error = getString(R.string.msg_main_field_required)
                return false
            }
            return true
        } ?: false
    }

    override fun showResult(I1: Double, I2: Double, I3: Double) {
        val dialog = MaterialDialog.Builder(this)
                .title(R.string.title_main_result_dialog)
                .positiveText(R.string.action_main_ok)
                .customView(R.layout.layout_result, false)
                .build()
        dialog.customView?.find<AppCompatTextView>(R.id.txt_i1)?.text = "I1 = ${I1}A"
        dialog.customView?.find<AppCompatTextView>(R.id.txt_i2)?.text = "I2 = ${I2}A"
        dialog.customView?.find<AppCompatTextView>(R.id.txt_i3)?.text = "I3 = ${I3}A"
        dialog.show()
    }
}
