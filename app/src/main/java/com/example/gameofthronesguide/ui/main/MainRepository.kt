package com.example.gameofthronesguide.ui.main

import com.example.gameofthronesguide.di.NetworkModule
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.network.CharacterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository  {

    private val characterService = NetworkModule.client.create(CharacterService::class.java)

    fun getCharacters():List<CharacterEntity>? {

        var characters : Call<List<CharacterEntity>> = characterService.getCharacters()
        var characterList : List<CharacterEntity>? = null
        characters.enqueue(object : Callback<List<CharacterEntity>> {
            override fun onResponse(call: Call<List<CharacterEntity>>, response: Response<List<CharacterEntity>>            ) {
                characterList = response.body()
            }


            override fun onFailure(call: Call<List<CharacterEntity>>, t: Throwable) {
                println("Hiba történt a hálózati kérés során: ${t.message}")
            }
        })
        Thread.sleep(5000)
        return characterList
    }



}
