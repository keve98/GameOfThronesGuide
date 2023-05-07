package com.example.gameofthronesguide.persistence

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.gameofthronesguide.model.CharacterModel

class CharacterDao {
    private val characterList = mutableListOf<CharacterModel>()
    private val characters = MutableLiveData<List<CharacterModel>>()

    init {
        characters.value = characterList
    }

    fun addCharacter(character: CharacterModel){
        characterList.add(character)
        characters.value = characterList
    }

    fun getCharacter(id: Int): CharacterModel {
        return characterList.single {character: CharacterModel ->  character.id == id}
    }

    fun getCharacters(): LiveData<List<CharacterModel>> {
        return characters
    }

    fun deleteCharacter(id: Int){
        characterList.removeAll { c -> c.id == id }
        characters.value = characterList
    }

    fun deleteCharacters(){
        characterList.clear()
        characters.value = characterList
    }

    fun updateCharacter(character: CharacterModel) {
        characterList.removeAll { c -> c.id == character.id }
        characterList.add(character)
        characters.value = characterList
    }
}