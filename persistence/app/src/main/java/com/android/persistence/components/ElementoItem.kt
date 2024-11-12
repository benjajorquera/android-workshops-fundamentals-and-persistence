package com.android.persistence.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.persistence.backend.ComprasDB
import com.android.persistence.backend.ElementoLista

@Composable
fun ElementoItem(elemento: ElementoLista, onClick: () -> Unit = {}) {
    val contexto = LocalContext.current
    val elementoListaDao = ComprasDB.getInstance(contexto).elementoListaDao()

    val (estado, setEstado) = remember { mutableStateOf(elemento.comprado == "Sí") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Checkbox(
            checked = estado,
            onCheckedChange = {
                setEstado(it)
                val nuevoEstado = if (it) "Sí" else "No"
                elementoListaDao.update(elemento.copy(comprado = nuevoEstado))
            },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.secondary,
                uncheckedColor = MaterialTheme.colorScheme.onBackground
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = elemento.nombre ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}