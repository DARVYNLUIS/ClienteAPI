package edu.ucne.clientes_api.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.ucne.clientes_api.presentation.edit.ClienteEditScreen
import edu.ucne.clientes_api.presentation.list.ClienteListScreen


@Composable
fun ClienteNavHost(navController: NavHostController) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ClienteScreen.ClienteList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ClienteScreen.ClienteList.route) {
                ClienteListScreen(navController = navController)
            }

            composable(
                route = ClienteScreen.EditCliente.route,
                arguments = listOf(
                    navArgument("clienteId") {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) { backStackEntry ->
                val clienteId = backStackEntry.arguments?.getInt("clienteId") ?: -1
                ClienteEditScreen(
                    navController = navController,
                    clienteId = clienteId
                )
            }
        }
    }
}
