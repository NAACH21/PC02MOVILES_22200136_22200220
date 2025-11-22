package dev.lchang.appue.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lchang.appue.presentation.screens.ListadoScreen
import dev.lchang.appue.presentation.screens.RegisterScreen
import dev.lchang.appue.presentation.viewmodel.TeamViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val teamViewModel: TeamViewModel = viewModel()
    val teams by teamViewModel.teams.collectAsState()

    NavHost(navController = navController, startDestination = "listado") {
        composable("register") {
            RegisterScreen(navController = navController) {
                team ->
                teamViewModel.addTeam(team)
                navController.navigate("listado")
            }
        }
        composable("listado") {
            ListadoScreen(navController = navController, teams = teams)
        }
    }
}
