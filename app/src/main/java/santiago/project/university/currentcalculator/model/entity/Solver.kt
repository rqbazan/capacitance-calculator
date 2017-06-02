package santiago.project.university.currentcalculator.model.entity

import org.jscience.physics.amount.Amount
import org.la4j.Vector
import org.la4j.linear.GaussianSolver
import org.la4j.matrix.DenseMatrix
import javax.measure.quantity.ElectricResistance

/**
 * Created by santiago on 01/06/17.
 */
class Solver {
    val converter = Converter()

    fun solve(v8: Volt, v9: Volt, v10: Volt, resistances: Array<out Resistance>): Vector {
        // uniformizar unidades para que el resultado sea en Amperes
        val rV8 = converter.toVolts(v8).value
        val rV9 = converter.toVolts(v9).value
        val rV10 = converter.toVolts(v10).value
        val rResistances = resistances.map {
            converter.toOhms(it).value
        }

        // calcular coeficientes para la matriz del sistema de equaciones
        val a1 = rResistances[0].plus(rResistances[2]).plus(rResistances[6]).estimatedValue
        val b1 = rResistances[2].times(-1).estimatedValue
        val c1 = 0.0

        val a2 = rResistances[2].times(-1).estimatedValue
        val b2 = rResistances[3].plus(rResistances[2]).plus(rResistances[1]).estimatedValue
        val c2 = rResistances[3].times(-1).estimatedValue

        val a3 = 0.0
        val b3 = rResistances[3].times(-1).estimatedValue
        val c3 = rResistances[5].plus(rResistances[4]).plus(rResistances[3]).estimatedValue

        val r1 = rV8.estimatedValue
        val r2 = rV9.times(-1).estimatedValue
        val r3 = rV10.times(-1).estimatedValue

        // setup de la matriz para resolver el sistema de ecuaciones
        val rawMatriz: Array<DoubleArray> = arrayOf(
                doubleArrayOf(a1, b1, c1, r1),
                doubleArrayOf(a2, b2, c2, r2),
                doubleArrayOf(a3, b3, c3, r3)
        )

        // resolucion del sistema de ecuaciones
        val matriz = DenseMatrix.from2DArray(rawMatriz)
        val gaussianSolver = GaussianSolver(matriz.slice(0, 0, 3, 3))
        return gaussianSolver.solve(matriz.getColumn(3))
    }
}