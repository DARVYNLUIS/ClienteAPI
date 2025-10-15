package edu.ucne.clientes_api.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.clientes_api.domain.cliente.model.Cliente
import edu.ucne.clientes_api.domain.cliente.usecases.GetClientesUseCase
import edu.ucne.clientes_api.domain.cliente.usecases.PostClienteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val getClientesUseCase: GetClientesUseCase,
    private val postClienteUseCase: PostClienteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ClienteUiState())
    val uiState: StateFlow<ClienteUiState> = _uiState

    fun onEvent(event: ClienteUiEvent) {
        when (event) {
            is ClienteUiEvent.LoadClientes -> loadClientes()
            is ClienteUiEvent.AddCliente -> addCliente(event.cliente)
        }
    }

    private fun loadClientes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val clientes = getClientesUseCase()
                _uiState.value = _uiState.value.copy(
                    clientes = clientes,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Error desconocido",
                    isLoading = false
                )
            }
        }
    }

    fun getClienteById(clienteId: Int): Cliente? {
        return _uiState.value.clientes.find { it.clienteId == clienteId }
    }

    private fun addCliente(cliente: Cliente) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                postClienteUseCase(cliente)

                val clientesActualizados = getClientesUseCase()
                _uiState.value = _uiState.value.copy(
                    clientes = clientesActualizados,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Error al guardar cliente",
                    isLoading = false
                )
            }
        }
    }
}
