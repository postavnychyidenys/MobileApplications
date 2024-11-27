package com.example.lab3

data class Debtor(
    val firstName: String,
    val lastName: String
): ListItem {
    override fun getItemType(): Int {
        return ListItem.DEBTOR_TYPE
    }
}
