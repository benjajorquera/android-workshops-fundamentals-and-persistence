package com.android.persistence.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.persistence.backend.ComprasDB
import com.android.persistence.backend.ElementoLista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementoDetalles(elemento: ElementoLista?, saveAction: () -> Unit) {
    val elementoDao = ComprasDB.getInstance(LocalContext.current).elementoListaDao()
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf(elemento?.nombre ?: "") }
    var buttonText by remember { mutableStateOf(if (elemento?.id != null) "Guardar" else "Crear") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del elemento") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    val elementoDB = ElementoLista(
                        id = elemento?.id,
                        nombre = nombre,
                        comprado = elemento?.comprado ?: "No"
                    )

                    if (elemento?.id != null) {
                        elementoDao.update(elementoDB)
                    } else {
                        elementoDao.insert(elementoDB)
                    }

                    buttonText = if (elemento?.id != null) "Guardar" else "Crear"
                    saveAction()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = buttonText, fontWeight = FontWeight.Bold)
        }

        if (elemento?.id != null) {
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        elementoDao.delete(elemento)
                        saveAction()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = saveAction,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}