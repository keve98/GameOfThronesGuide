package com.example.gameofthronesguide.persistence

import androidx.lifecycle.MutableLiveData

class CharacterDao {
    private val characterList = mutableListOf<Character>()
    private val characters = MutableLiveData<List<Character>>()

    init {
        characters.value = characterList
    }

    fun addCharacter(character: Character){
        throw NotImplementedError()
    }

    fun getCharacter(id: Int){
        throw NotImplementedError()
    }

    fun getCharacters(){
        throw NotImplementedError()
    }
}