package edu.ucne.clientes_api


import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClientesApp : Application()
 {
    override fun onCreate() {
        super.onCreate()
    }
}

