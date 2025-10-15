package edu.ucne.clientes_api.data.repository

import edu.ucne.clientes_api.data.local.entities.ClienteEntity
import edu.ucne.darvyn_lavandierap2_p1.data.local.mapper.ClienteMapper
import edu.ucne.clientes_api.data.local.api.RetrofitInstance
import edu.ucne.clientes_api.domain.cliente.model.Cliente
import edu.ucne.clientes_api.data.local.api.ClienteApi
import edu.ucne.clientes_api.domain.cliente.repository.ClienteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.collections.map

class ClienteRepositoryImpl(
    private val api: ClienteApi
) : ClienteRepository {

    override suspend fun getClientesFromApi(): List<Cliente> {
        val dtos = api.getClientes()
        return dtos.map { ClienteMapper.toDomain(it) }
    }

    override suspend fun postClienteToApi(cliente: Cliente): Cliente {
        val dto = ClienteMapper.toDto(cliente)
        val response = api.postCliente(dto)
        return ClienteMapper.toDomain(response)
    }

    override fun getClientesLocalFlow(): Flow<List<Cliente>> {
        throw NotImplementedError("No se usa almacenamiento local")
    }

    override suspend fun insertClienteLocal(cliente: Cliente) {
        throw NotImplementedError("No se usa almacenamiento local")
    }
}
