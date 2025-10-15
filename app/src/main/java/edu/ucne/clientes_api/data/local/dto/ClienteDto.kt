package edu.ucne.clientes_api.data.remote.dto


data class ClienteDto(
    val clienteId: Int = 0,
    val fechaIngreso: String = "",
    val nombres: String = "",
    val direccion: String = "",
    val rnc: String = "",
    val limiteCredito: Double = 0.0
)
