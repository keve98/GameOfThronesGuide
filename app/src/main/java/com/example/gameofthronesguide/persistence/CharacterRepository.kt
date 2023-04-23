package com.example.gameofthronesguide.persistence

class CharacterRepository private constructor(private val characterDao: CharacterDao) {
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