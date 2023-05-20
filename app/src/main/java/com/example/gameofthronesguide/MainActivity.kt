package com.example.gameofthronesguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameofthronesguide.di.NetworkModule
import com.example.gameofthronesguide.di.RepositoryModule
import com.example.gameofthronesguide.ui.main.CharacterRepository
import com.example.gameofthronesguide.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    var characterRepository : CharacterRepository?= null

    var viewModel : MainViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}