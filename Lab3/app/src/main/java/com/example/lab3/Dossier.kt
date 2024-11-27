package com.example.lab3

data class Dossier(
    val firstName: String,
    val lastName: String
): ListItem {
    override fun getItemType(): Int {
        return ListItem.DOSSIER_TYPE
    }
}
