package com.example.gameofthronesguide.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.network.CharacterService

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CHARACTERS")
    fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id_")
    suspend fun getCharacter(id_: Long): CharacterEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterEntity: CharacterEntity) : Long

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterEntity)
}