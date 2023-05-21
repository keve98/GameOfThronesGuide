package com.example.gameofthronesguide

import com.example.gameofthronesguide.di.NetworkModule
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.network.CharacterService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun testRetrofitInstance() {
        val instance: Retrofit = NetworkModule.client
        assert(instance.baseUrl().url().toString() == "https://thronesapi.com/api/v2/")
    }

    private lateinit var characterService: CharacterService

    @Before
    fun setup() {
        // Inicializálás a tesztelendő karakterszolgáltatás példányával
        characterService = createCharacterService()
    }

    @Test
    fun testGetCharacters() {
        val call: Call<List<CharacterEntity>> = characterService.getCharacters()
        var characters: List<CharacterEntity>? = null

        call.enqueue(object : Callback<List<CharacterEntity>> {
            override fun onResponse(
                call: Call<List<CharacterEntity>>,
                response: Response<List<CharacterEntity>>
            ) {
                Assert.assertTrue(response.isSuccessful)
                Assert.assertNotNull(response.body())

                characters = response.body()
            }

            override fun onFailure(call: Call<List<CharacterEntity>>, t: Throwable) {
                Assert.fail("Hálózati hiba történt: ${t.message}")
            }
        })

        Thread.sleep(2000)

        Assert.assertNotNull(characters)
        Assert.assertTrue(characters!!.isNotEmpty())
    }

    @Test
    fun testGetCharacterDetails() {
        val characterId = "1"

        val call: Call<CharacterEntity> = characterService.getCharacterDetails(characterId)
        var character: CharacterEntity? = null

        call.enqueue(object : Callback<CharacterEntity> {
            override fun onResponse(
                call: Call<CharacterEntity>,
                response: Response<CharacterEntity>
            ) {
                Assert.assertTrue(response.isSuccessful)
                Assert.assertNotNull(response.body())

                character = response.body()
            }

            override fun onFailure(call: Call<CharacterEntity>, t: Throwable) {
                Assert.fail("Hálózati hiba történt: ${t.message}")
            }
        })

        Thread.sleep(2000)

        Assert.assertNotNull(character)
        Assert.assertEquals(characterId, character!!.id)
    }

    private fun createCharacterService(): CharacterService {
        return NetworkModule.client.create(CharacterService::class.java)
    }
}