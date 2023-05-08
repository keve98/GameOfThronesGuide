package com.example.gameofthronesguide.ui.details

import androidx.lifecycle.ViewModel
import com.example.gameofthronesguide.model.CharacterModel

class DetailsViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    var id : Int = -1

    val character: CharacterModel
        get() = characterRepository.getCharacter(id = id)
}