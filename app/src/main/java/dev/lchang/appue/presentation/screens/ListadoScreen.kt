package dev.lchang.appue.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.lchang.appue.data.model.Team

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoScreen(navController: NavController, teams: List<Team>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Equipos Registrados") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("register") }) {
                Icon(Icons.Default.Add, contentDescription = "Registrar equipo")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(teams) { team ->
                TeamItem(team = team)
            }
        }
    }
}

@Composable
fun TeamItem(team: Team) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(team.imageUrl)
                    .crossfade(true)
                    .error(android.R.drawable.ic_menu_gallery) // Imagen de error
                    .build(),
                contentDescription = "Escudo de ${team.name}",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = team.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Fundado en: ${team.foundationYear}",
                    fontSize = 14.sp
                )
                Text(
                    text = "Títulos ganados: ${team.titles}",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListadoScreenPreview() {
    val navController = androidx.navigation.compose.rememberNavController()
    val teams = listOf(
        Team("Real Madrid", "1902", "35", "url"),
        Team("FC Barcelona", "1899", "27", "url")
    )
    ListadoScreen(navController, teams)
}
