package com.fundamentals

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.fundamentals.model.EmpleadoRegular

class SueldoHonorariosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PantallaHonorarios() }
    }
}

class SueldoRegularActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sueldo_regular)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnCalcular.setOnClickListener {
            val resultado = findViewById<TextView>(R.id.tvResultado)
            val sueldoBruto = findViewById<EditText>(R.id.etSueldoBruto)
            val sueldoBrutoInput = sueldoBruto.text.toString().toDoubleOrNull() ?: 0.0
            val empleadoRegular = EmpleadoRegular(sueldoBrutoInput)
            val sueldo = empleadoRegular.calcularLiquido()
            resultado.text = "El sueldo es $sueldo"
        }
    }

    fun volverAtras(view: View) {
        finish()
    }
}