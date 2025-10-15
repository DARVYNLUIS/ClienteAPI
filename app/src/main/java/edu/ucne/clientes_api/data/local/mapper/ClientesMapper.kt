package edu.ucne.darvyn_lavandierap2_p1.data.local.mapper

import edu.ucne.clientes_api.data.local.entities.ClienteEntity
import edu.ucne.clientes_api.data.remote.dto.ClienteDto
import edu.ucne.clientes_api.domain.cliente.model.Cliente

object ClienteMapper {

    fun toDomain(entity: ClienteEntity): Cliente =
        Cliente(
            clienteId = entity.clienteId,
            fechaIngreso = entity.fechaIngreso,
            nombres = entity.nombres,
            direccion = entity.direccion,
            rnc = entity.rnc,
            limiteCredito = entity.limiteCredito
        )

    fun toEntity(domain: Cliente): ClienteEntity =
        ClienteEntity(
            clienteId = domain.clienteId,
            fechaIngreso = domain.fechaIngreso,
            nombres = domain.nombres,
            direccion = domain.direccion,
            rnc = domain.rnc,
            limiteCredito = domain.limiteCredito
        )

    fun toDomain(dto: ClienteDto): Cliente =
        Cliente(
            clienteId = dto.clienteId,
            fechaIngreso = dto.fechaIngreso,
            nombres = dto.nombres,
            direccion = dto.direccion,
            rnc = dto.rnc,
            limiteCredito = dto.limiteCredito
        )

    // --- Domain → DTO ---
    fun toDto(domain: Cliente): ClienteDto =
        ClienteDto(
            clienteId = domain.clienteId,
            fechaIngreso = domain.fechaIngreso,
            nombres = domain.nombres,
            direccion = domain.direccion,
            rnc = domain.rnc,
            limiteCredito = domain.limiteCredito
        )
}
