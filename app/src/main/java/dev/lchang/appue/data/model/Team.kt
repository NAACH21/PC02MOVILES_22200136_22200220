package dev.lchang.appue.data.model

data class Team(
    val name: String = "",
    val foundationYear: String = "",
    val titles: String = "",
    val imageUrl: String = ""
){
    // Constructor sin argumentos requerido por Firestore
    constructor() : this("", "", "", "")
}
