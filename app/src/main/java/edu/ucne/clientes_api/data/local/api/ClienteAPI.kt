package edu.ucne.clientes_api.data.local.api


import  edu.ucne.clientes_api.data.remote.dto.ClienteDto
import retrofit2.http.*

interface ClienteApi {

    @GET("api/Clientes")
    suspend fun getClientes(): List<ClienteDto>

    @POST("api/Clientes")
    suspend fun postCliente(@Body cliente: ClienteDto): ClienteDto
}
