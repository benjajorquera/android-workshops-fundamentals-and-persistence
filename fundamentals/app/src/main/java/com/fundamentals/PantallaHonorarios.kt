package com.fundamentals

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fundamentals.model.EmpleadoHonorarios

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PantallaHonorarios() {
    var sueldoBruto by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val contexto = LocalContext.current as ComponentActivity

    Column(
        modifier =
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Empleado a Honorarios",
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            placeholder = {
                Text(
                    "Sueldo Bruto"
                )
            },
            value = sueldoBruto,
            onValueChange = { sueldoBruto = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val sueldoBrutoInput = sueldoBruto.toDoubleOrNull() ?: 0.0
                val sueldo = EmpleadoHonorarios(sueldoBrutoInput).calcularLiquido()
                resultado = "El sueldo es de $sueldo"
            })
        { Text("Calcular Sueldo") }
        Spacer(modifier = Modifier.height(20.dp))
        Text(resultado)
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Button(onClick = { contexto.finish() }) { Text("<- Volver") }
    }
}