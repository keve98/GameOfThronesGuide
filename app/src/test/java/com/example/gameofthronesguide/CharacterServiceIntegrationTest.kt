package com.example.gameofthronesguide

import com.example.gameofthronesguide.network.CharacterService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterServiceIntegrationTest {
    private lateinit var characterService: CharacterService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thronesapi.com/api/v2/") // Helyettesítsd a valós API alapú URL-lel
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        characterService = retrofit.create(CharacterService::class.java)
    }

    @Test
    fun testGetCharacters() {
        val call = characterService.getCharacters()
        val response = call.execute()

        Assert.assertTrue(response.isSuccessful)
        Assert.assertNotNull(response.body())

        val characters = response.body()
        Assert.assertTrue(characters!!.isNotEmpty())
    }

    @Test
    fun testGetCharacterDetails() {
        val characterId = "1"

        val call = characterService.getCharacterDetails(characterId)
        val response = call.execute()

        Assert.assertTrue(response.isSuccessful)
        Assert.assertNotNull(response.body())

        val character = response.body()
        Assert.assertEquals(characterId, character!!.id.toString())
    }
}
