package dev.lchang.appue.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import dev.lchang.appue.data.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {

    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> = _teams

    private val db = FirebaseFirestore.getInstance()
    private val teamsCollection = db.collection("equipos")

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        teamsCollection.addSnapshotListener { snapshots, error ->
            if (error != null) {
                Log.w("TeamViewModel", "Error al obtener equipos", error)
                return@addSnapshotListener
            }
            if (snapshots != null) {
                val teamList = snapshots.toObjects(Team::class.java)
                _teams.value = teamList
                Log.d("TeamViewModel", "Equipos cargados: ${teamList.size}")
            }
        }
    }

    fun addTeam(team: Team) {
        viewModelScope.launch {
            teamsCollection.add(team)
                .addOnSuccessListener { documentReference ->
                    Log.d("TeamViewModel", "Equipo añadido con ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TeamViewModel", "Error al añadir equipo", e)
                }
        }
    }
}
