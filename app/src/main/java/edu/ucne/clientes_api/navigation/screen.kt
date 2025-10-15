package edu.ucne.clientes_api.navigation

sealed class ClienteScreen(val route: String) {
    object ClienteList : ClienteScreen("cliente_list")
    object EditCliente : ClienteScreen("edit_cliente/{clienteId}") {
        fun createRoute(clienteId: Int) = "edit_cliente/$clienteId"
    }
}
