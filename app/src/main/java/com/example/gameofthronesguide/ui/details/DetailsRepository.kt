package com.example.gameofthronesguide.ui.details


import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.persistence.CharacterDao
import javax.inject.Inject

class DetailsRepository {

    @Inject
    lateinit var characterDao: CharacterDao

    suspend fun getCharacter(id: Long) : CharacterEntity?{
        return characterDao.getCharacter(id);
    }

}