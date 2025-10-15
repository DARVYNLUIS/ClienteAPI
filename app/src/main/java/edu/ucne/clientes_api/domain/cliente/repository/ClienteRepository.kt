package edu.ucne.clientes_api.domain.cliente.repository

import edu.ucne.clientes_api.domain.cliente.model.Cliente
import kotlinx.coroutines.flow.Flow

interface ClienteRepository {
    suspend fun getClientesFromApi(): List<Cliente>
    suspend fun postClienteToApi(cliente: Cliente): Cliente
    fun getClientesLocalFlow(): Flow<List<Cliente>>
    suspend fun insertClienteLocal(cliente: Cliente)
}
