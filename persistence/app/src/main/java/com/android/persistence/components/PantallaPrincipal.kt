package com.android.persistence.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.persistence.backend.ComprasDB
import com.android.persistence.backend.ElementoLista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class Accion { LISTAR, CREAR, EDITAR }

@Composable
fun PantallaPrincipal() {
    val contexto = LocalContext.current

    var elementos by remember { mutableStateOf(emptyList<ElementoLista>()) }

    LaunchedEffect(elementos) {
        withContext(Dispatchers.IO) {
            val database = ComprasDB.getInstance(contexto)
            elementos = database.elementoListaDao().getAll()
        }
    }

    var accion by remember { mutableStateOf(Accion.LISTAR) }
    var seleccion by remember { mutableStateOf<ElementoLista?>(null) }

    val saveAction = {
        accion = Accion.LISTAR
        elementos = emptyList()
    }

    when (accion) {
        Accion.CREAR -> ElementoDetalles(null, saveAction)
        Accion.EDITAR -> ElementoDetalles(seleccion, saveAction)
        else -> ListaElementos(
            elementos,
            addAction = { accion = Accion.CREAR },
            editAction = { elemento ->
                seleccion = elemento
                accion = Accion.EDITAR
            }
        )
    }
}

@Composable
fun ListaElementos(
    elementos: List<ElementoLista>,
    addAction: () -> Unit,
    editAction: (ElementoLista) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Lista de Compras",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        val elementosDB: List<ElementoLista> = elementos.ifEmpty {
            val dao = ComprasDB.getInstance(LocalContext.current).elementoListaDao()
            dao.getAll()
        }
        if (elementosDB.isNotEmpty()) {
            LazyColumn(modifier = Modifier.weight(9f)) {
                items(elementosDB) { elemento ->
                    ElementoItem(elemento) { editAction(elemento) }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Lista de elementos vacía.")
            }
        }

        Button(
            onClick = { addAction() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text("Agregar", fontWeight = FontWeight.Bold)
        }
    }
}
