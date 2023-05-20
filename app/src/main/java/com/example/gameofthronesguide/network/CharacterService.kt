package com.example.gameofthronesguide.network

import com.example.gameofthronesguide.model.CharacterEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CharacterService {
    @GET("api/v2/characters")
    fun getCharacters(): Call<CharacterEntity>

    @GET("api/v2/Characters/{id}")
    fun getCharacterDetails(@Path("id") id: String): Call<CharacterEntity>

    @POST("api/v2/characters")
    fun addCharacter(@Body character: CharacterEntity)
}