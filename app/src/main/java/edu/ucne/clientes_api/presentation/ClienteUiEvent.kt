package edu.ucne.clientes_api.presentation

import edu.ucne.clientes_api.domain.cliente.model.Cliente

sealed class ClienteUiEvent {
    object LoadClientes : ClienteUiEvent()
    data class AddCliente(val cliente: Cliente) : ClienteUiEvent()
}
