package com.example.gameofthronesguide.network

import retrofit2.Call
import retrofit2.http.GET
interface CharacterService {
    @GET("api/v2/characters")
    fun getCharacters(): Call<List<Character>>
}