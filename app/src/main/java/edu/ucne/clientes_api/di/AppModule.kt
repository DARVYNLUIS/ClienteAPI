package edu.ucne.clientes_api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.clientes_api.data.local.api.RetrofitInstance
import edu.ucne.clientes_api.data.repository.ClienteRepositoryImpl
import edu.ucne.clientes_api.domain.cliente.repository.ClienteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClienteRepository(): ClienteRepository {
        return ClienteRepositoryImpl(RetrofitInstance.api)
    }
}
