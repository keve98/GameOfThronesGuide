package com.example.gameofthronesguide.di

import android.content.Context
import androidx.room.Room
import com.example.gameofthronesguide.persistence.AppDatabase
import com.example.gameofthronesguide.persistence.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Singleton
    @Provides
    fun provideCharacterDatabase( @ApplicationContext app: Context) =
        Room.databaseBuilder(app, AppDatabase::class.java,"characters").build()

    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }
}