package com.example.gameofthronesguide.persistence

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.gameofthronesguide.model.CharacterModel
import com.example.gameofthronesguide.network.RestApiClient
import com.google.gson.Gson
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CharacterRepository private constructor(private val characterDao: CharacterDao) {
    fun addCharacter(character: CharacterModel){
        characterDao.addCharacter(character)
    }

    fun getCharacter(id: Int): CharacterModel{
        return characterDao.getCharacter(id)
    }

    fun getCharacters(): LiveData<List<CharacterModel>>{
        val retrofit = RestApiClient.client
        val restApiClient = RestApiClient.createRestApiInterface(retrofit)
        val callApi = restApiClient.getCharacters()
        val gson = Gson()

        callApi.enqueue(object : Callback<CharacterModel> {
            override fun onFailure(call: Call<CharacterModel>?, t: Throwable?) {
                Log.v("FAILURE: Retrofit", " getCharacters call failed!")
            }

            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                val characters = JSONArray(response.body())
                characterDao.deleteCharacters()

                for (i in 0 until characters.length()){
                    val c = gson.fromJson(characters.getString(i), CharacterModel::class.java)
                    characterDao.addCharacter(c)
                }
            }
        })
        return characterDao.getCharacters()
    }

    fun updateCharacter(character: CharacterModel){
        characterDao.updateCharacter(character = character)
    }

    fun deleteCharacter(id: Int){
        characterDao.deleteCharacter(id)
    }

    companion object {
        @Volatile private var instance: CharacterRepository? = null

        fun getInstance(characterDao: CharacterDao) =
            instance ?: synchronized(this) {
                instance ?: CharacterRepository(characterDao).also { instance = it }
            }
    }
}