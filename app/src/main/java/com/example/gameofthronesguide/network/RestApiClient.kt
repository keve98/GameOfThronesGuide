package com.example.gameofthronesguide.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RestApiClient {

    private const val URL = "https://thronesapi.com/"
    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            }
            return this.retrofit!!
        }

    fun createRestApiInterface(retrofit: Retrofit): CharacterService{
        return retrofit.create(CharacterService::class.java)
    }
}