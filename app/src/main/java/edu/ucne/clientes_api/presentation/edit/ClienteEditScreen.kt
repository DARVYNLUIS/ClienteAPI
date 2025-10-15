package edu.ucne.clientes_api.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.clientes_api.domain.cliente.model.Cliente
import edu.ucne.clientes_api.presentation.ClienteUiEvent
import edu.ucne.clientes_api.presentation.ClienteViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteEditScreen(
    navController: NavController,
    clienteId: Int,
    viewModel: ClienteViewModel = hiltViewModel()
) {
    // Estados para los campos
    var nombre by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var rnc by remember { mutableStateOf("") }
    var limiteCredito by remember { mutableStateOf("") }

    // Aquí podrías cargar los datos si clienteId != -1 (edición)
    LaunchedEffect(clienteId) {
        if (clienteId != -1) {
            val cliente = viewModel.getClienteById(clienteId)
            cliente?.let {
                nombre = it.nombres
                direccion = it.direccion
                rnc = it.rnc
                limiteCredito = it.limiteCredito.toString()
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(if (clienteId == -1) "Agregar Cliente" else "Editar Cliente") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = rnc,
                onValueChange = { rnc = it },
                label = { Text("RNC") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = limiteCredito,
                onValueChange = { limiteCredito = it },
                label = { Text("Límite de Crédito") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Convierte limiteCredito a Double o Int según tu modelo
                    val cliente = Cliente(
                        clienteId = clienteId,
                        nombres = nombre,
                        direccion = direccion,
                        rnc = rnc,
                        limiteCredito = limiteCredito.toDoubleOrNull() ?: 0.0,
                        fechaIngreso = "" // Aquí puedes poner la fecha actual o mantener la existente
                    )

                    viewModel.onEvent(ClienteUiEvent.AddCliente(cliente))
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}
