package com.example.gameofthronesguide.ui.main

import androidx.lifecycle.ViewModel
import com.example.gameofthronesguide.persistence.CharacterRepository

class MainViewModel(private val characterRepository: CharacterRepository): ViewModel() {
    fun getCharacters(){
        return characterRepository.getCharacters()
    }
}