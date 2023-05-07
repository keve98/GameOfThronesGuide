package com.example.gameofthronesguide.persistence

class AppDatabase private constructor(){
    var characterDao = CharacterDao()
        private set

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: AppDatabase().also { instance = it }
            }
    }
}