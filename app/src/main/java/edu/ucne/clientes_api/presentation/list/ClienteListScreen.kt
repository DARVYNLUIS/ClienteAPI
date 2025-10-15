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
import edu.ucne.clientes_api.presentation.ClienteUiEvent
import edu.ucne.clientes_api.presentation.ClienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClienteListScreen(
    navController: NavController,
    viewModel: ClienteViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ClienteUiEvent.LoadClientes)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Clientes") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("edit_cliente/-1") }) {
                Text("+")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                state.error != null -> Text("Error: ${state.error}")
                else -> LazyColumn {
                    items(state.clientes) { cliente ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("edit_cliente/${cliente.clienteId}")
                                }
                                .padding(16.dp)
                        ) {
                            Text("ID: ${cliente.clienteId}")
                            Text("Fecha: ${cliente.fechaIngreso}")
                            Text("Nombre: ${cliente.nombres}")
                            Text("Dirección: ${cliente.direccion}")
                            Text("RNC: ${cliente.rnc}")
                            Text("Límite: ${cliente.limiteCredito}")
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
