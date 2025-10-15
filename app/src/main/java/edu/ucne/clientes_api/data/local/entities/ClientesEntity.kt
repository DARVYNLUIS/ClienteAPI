package edu.ucne.clientes_api.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    val clienteId: Int = 0,
    val fechaIngreso: String,
    val nombres: String,
    val direccion: String,
    val rnc: String,
    val limiteCredito: Double
)
