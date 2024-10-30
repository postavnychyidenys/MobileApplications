package com.example.lab1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val textsList = listOf("Сам ти кнопка", "Давай по новой", "* 0")
    private var lastText: String? = null

    fun changeText() {
        val newText = textsList.shuffled().firstOrNull { it != lastText }

        if (newText != null) {
            _text.value = newText
            lastText = newText
        }
    }
}