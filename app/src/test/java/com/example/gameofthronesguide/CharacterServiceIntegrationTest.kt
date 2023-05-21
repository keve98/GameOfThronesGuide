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
        // Inicializálás a valós szerverrel
        val retrofit = Retrofit.Builder()
            .baseUrl("https://thronesapi.com/api/v2/") // Helyettesítsd a valós API alapú URL-lel
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Létrehozzuk a tesztelendő karakterszolgáltatás példányát
        characterService = retrofit.create(CharacterService::class.java)
    }

    @Test
    fun testGetCharacters() {
        // Hívjuk meg a getCharacters() metódust
        val call = characterService.getCharacters()
        val response = call.execute()

        // Ellenőrizzük a választ és a státuszkódot
        Assert.assertTrue(response.isSuccessful)
        Assert.assertNotNull(response.body())

        // Ellenőrizzük, hogy a karaktereket sikeresen lekértük
        val characters = response.body()
        Assert.assertTrue(characters!!.isNotEmpty())
    }

    @Test
    fun testGetCharacterDetails() {
        // Karakter azonosító
        val characterId = "1"

        // Hívjuk meg a getCharacterDetails() metódust az adott azonosítóval
        val call = characterService.getCharacterDetails(characterId)
        val response = call.execute()

        // Ellenőrizzük a választ és a státuszkódot
        Assert.assertTrue(response.isSuccessful)
        Assert.assertNotNull(response.body())

        // Ellenőrizzük, hogy a karakter sikeresen lekérve lett
        val character = response.body()
        Assert.assertEquals(characterId, character!!.id.toString())
    }
}
