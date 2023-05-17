package com.example.gameofthronesguide.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(@PrimaryKey(autoGenerate = true)
                           val id: Int,
                           val firstName: String,
                           val lastName: String,
                           val fullName: String,
                           val title: String,
                           val family: String,
                           val image: String,
                           val imageUrl: String
                           )
