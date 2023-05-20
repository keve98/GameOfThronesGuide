package com.example.gameofthronesguide.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "characters")
data class CharacterEntity(@PrimaryKey(autoGenerate = true)
                            @Json(name = "id")
                           val id: Int,
                           @Json(name = "firstName")
                           val firstName: String,
                           @Json(name = "lastName")
                           val lastName: String,
                           @Json(name = "fullName")
                           val fullName: String,
                           @Json(name = "title")
                           val title: String,
                           @Json(name = "family")
                           val family: String,
                           @Json(name = "image")
                           val image: String,
                           @Json(name = "imageUrl")
                           val imageUrl: String
                           )
