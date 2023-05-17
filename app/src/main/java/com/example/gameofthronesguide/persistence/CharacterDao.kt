package com.example.gameofthronesguide.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.gameofthronesguide.model.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>


    @Insert
    suspend fun insertCharacter(characterEntity: CharacterEntity) : Long

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterEntity)
}