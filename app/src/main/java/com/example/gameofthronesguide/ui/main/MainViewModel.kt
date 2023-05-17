package com.example.gameofthronesguide.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.gameofthronesguide.model.CharacterModel

class MainViewModel(private val characterRepository: CharacterRepository): ViewModel() {
    val characters: LiveData<List<CharacterModel>>
        get() = characterRepository.getCharacters()
}