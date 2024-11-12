package com.fundamentals.model

abstract class Empleado(var sueldoBruto: Double) {
    abstract fun calcularLiquido(): Double
}

class EmpleadoHonorarios(sueldoBruto: Double) : Empleado(sueldoBruto) {
    override fun calcularLiquido(): Double = sueldoBruto - (sueldoBruto * 0.13)
}

class EmpleadoRegular(sueldoBruto: Double) : Empleado(
    sueldoBruto
) {
    override fun calcularLiquido(): Double = sueldoBruto - (sueldoBruto * 0.2)
}