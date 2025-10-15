package edu.ucne.clientes_api.domain.cliente.usecases

import edu.ucne.clientes_api.domain.cliente.model.Cliente
import edu.ucne.clientes_api.domain.cliente.repository.ClienteRepository
import javax.inject.Inject

class GetClientesUseCase @Inject constructor(
    private val repository: ClienteRepository
) {
    suspend operator fun invoke(): List<Cliente> {
        return repository.getClientesFromApi()
    }
}
