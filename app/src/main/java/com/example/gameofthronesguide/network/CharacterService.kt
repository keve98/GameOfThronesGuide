package com.example.gameofthronesguide.network

import com.example.gameofthronesguide.model.CharacterEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CharacterService {
    @GET("characters")
    fun getCharacters(): Call<List<CharacterEntity>>

    @GET("characters/{id}")
    fun getCharacterDetails(@Path("id") id: String): Call<CharacterEntity>

    @POST("characters")
    fun addCharacter(@Body character: CharacterEntity)
}