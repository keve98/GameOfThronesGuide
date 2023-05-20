package com.example.gameofthronesguide.di

import com.example.gameofthronesguide.network.CharacterService
import com.example.gameofthronesguide.persistence.CharacterDao
import com.example.gameofthronesguide.ui.main.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        characterService: CharacterService,
        characterDao: CharacterDao
    ): CharacterRepository{
        return CharacterRepository(characterDao, characterService)
    }

}