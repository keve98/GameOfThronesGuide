package com.example.gameofthronesguide.ui.main

import androidx.lifecycle.ViewModel
import com.example.gameofthronesguide.model.CharacterEntity

//@HiltViewModel
class MainViewModel /*@Inject constructor(private val characterRepository: CharacterRepository)*/: ViewModel() {

    private val mainRepository : MainRepository = MainRepository()

    fun getCharacters() : List<CharacterEntity>?{

       return mainRepository.getCharacters()
    }

/*

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    init {
        Timber.d("injection MainViewModel")

    }

    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }*/


}