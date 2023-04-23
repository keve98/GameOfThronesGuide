package com.example.gameofthronesguide.ui.details

import androidx.lifecycle.ViewModel
import com.example.gameofthronesguide.persistence.CharacterRepository

class DetailsViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    fun getCharacterDetails(id: Int) {
        return characterRepository.getCharacter(id)
    }
}