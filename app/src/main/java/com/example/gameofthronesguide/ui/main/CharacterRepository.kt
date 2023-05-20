package com.example.gameofthronesguide.ui.main

import com.example.gameofthronesguide.di.NetworkModule
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.network.CharacterService
import com.example.gameofthronesguide.persistence.CharacterDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


import javax.inject.Inject

class CharacterRepository /*@Inject constructor(private val characterService: CharacterService, private val characterDao: CharacterDao)*/ {

    private val characterService = NetworkModule.client.create(CharacterService::class.java)

    fun getCharacters():List<Character>? {
        var characters=characterService.getCharacters()
        var characterList : List<Character>? = null
        characters.enqueue(object : Callback<List<Character>> {
            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>            ) {
                if (response.isSuccessful) {
                    val characters = response.body()
                    characterList = response.body()
                    // A karakterek további kezelése
                    characters?.forEach { character ->
                        println(character)
                    }
                } else {
                    // Kezelés, ha a kérés nem sikerült
                    println("Hiba történt a hálózati kérés során.")
                }
            }


            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                // Hiba kezelése
                println("Hiba történt a hálózati kérés során: ${t.message}")
            }
        })
        return characterList
    }

//    init {
//        val characters = getCharacters()
//        GlobalScope.launch {
//        for(c in characters){
//            var l = characterDao.insertCharacter(c)
//            print(c.fullName)
//            }
//        }
//    }
}
