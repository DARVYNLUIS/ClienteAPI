package edu.ucne.clientes_api.domain.cliente.model


data class Cliente(
    val clienteId: Int = 0,
    val fechaIngreso: String = "", // <-- agregado
    val nombres: String = "",
    val direccion: String = "",
    val rnc: String = "",
    val limiteCredito: Double = 0.0
)

