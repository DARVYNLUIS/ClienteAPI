package edu.ucne.clientes_api.presentation


import edu.ucne.clientes_api.domain.cliente.model.Cliente

data class ClienteUiState(
    val clientes: List<Cliente> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
