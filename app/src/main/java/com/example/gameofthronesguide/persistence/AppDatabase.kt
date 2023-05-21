package com.example.gameofthronesguide.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gameofthronesguide.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun characterDao() : CharacterDao
}