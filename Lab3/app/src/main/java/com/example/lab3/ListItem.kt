package com.example.lab3

interface ListItem {
    fun getItemType(): Int

    companion object{
        const val DEBTOR_TYPE = 1
        const val DOSSIER_TYPE = 2
    }
}