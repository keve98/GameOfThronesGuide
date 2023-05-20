package com.example.gameofthronesguide.ui.main

import androidx.annotation.WorkerThread
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.network.CharacterService
import com.example.gameofthronesguide.persistence.CharacterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val characterDao: CharacterDao, private val characterService: CharacterService) {

    init{
        Timber.d("Injection CharacterRepository")
    }

    @WorkerThread
    fun loadCharacters(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val characters: List<CharacterEntity> = characterDao.getAllCharacters()
        emit(characters)
    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

}
