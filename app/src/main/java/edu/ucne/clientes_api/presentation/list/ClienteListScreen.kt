package edu.ucne.clientes_api.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.clientes_api.presentation.ClienteViewModel
import edu.ucne.clientes_api.navigation.ClienteScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteListScreen(
    navController: NavController,
    viewModel: ClienteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(edu.ucne.clientes_api.presentation.ClienteUiEvent.LoadClientes)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Clientes") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navegar a crear cliente nuevo
                    navController.navigate(ClienteScreen.EditCliente.createRoute(-1))
                }
            ) {
                Text("+")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                }
                state.error != null -> {
                    Text("Error: ${state.error}")
                }
                else -> {
                    LazyColumn {
                        items(state.clientes) { cliente ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Navegar a editar cliente
                                        navController.navigate(
                                            ClienteScreen.EditCliente.createRoute(cliente.clienteId)
                                        )
                                    }
                                    .padding(16.dp)
                            ) {
                                Text(text = "ID: ${cliente.clienteId}")
                                Text(text = "Fecha Ingreso: ${cliente.fechaIngreso}")
                                Text(text = "Nombre: ${cliente.nombres}")
                                Text(text = "Dirección: ${cliente.direccion}")
                                Text(text = "RNC: ${cliente.rnc}")
                                Text(text = "Límite de Crédito: ${cliente.limiteCredito}")
                                Spacer(modifier = Modifier.height(8.dp))
                                Divider()
                            }
                        }
                    }
                }
            }
        }
    }
}
