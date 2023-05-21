package com.example.gameofthronesguide.ui.main

import androidx.lifecycle.ViewModel
import com.example.gameofthronesguide.model.CharacterEntity

class MainViewModel : ViewModel() {

    private val mainRepository : MainRepository = MainRepository()

    fun getCharacters() : List<CharacterEntity>?{
       return mainRepository.getCharacters()
    }
}