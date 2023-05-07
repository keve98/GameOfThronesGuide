package com.example.gameofthronesguide.persistence

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.gameofthronesguide.model.CharacterModel
import com.example.gameofthronesguide.network.RestApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CharacterRepository private constructor(private val characterDao: CharacterDao) {
    fun addCharacter(character: CharacterModel){
        characterDao.addCharacter(character)
    }

    fun getCharacter(id: Int): CharacterModel{
        return characterDao.getCharacter(id);
    }

    fun getCharacters(): LiveData<List<CharacterModel>>{
        val retrofit = RestApiClient.client
        val restApiClient = RestApiClient.createRestApiInterface(retrofit)
        val callApi = restApiClient.getCharacters()

        callApi.enqueue(object : Callback<CharacterModel> {
            override fun onFailure(call: Call<CharacterModel>?, t: Throwable?) {
                Log.v("FAILURE: Retrofit", " getCharacters call failed!")
            }

            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                TODO("Not yet implemented")
            }
        })
    }
}