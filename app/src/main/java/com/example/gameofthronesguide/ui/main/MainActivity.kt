package com.example.gameofthronesguide.ui.main

import android.app.appsearch.GlobalSearchSession
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.gameofthronesguide.GOTApp
import com.example.gameofthronesguide.R
import com.example.gameofthronesguide.adapter.CharacterAdapter
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.persistence.AppDatabase
import com.example.gameofthronesguide.persistence.CharacterDao
import com.example.gameofthronesguide.ui.details.CharacterDetailsActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import res.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private var adapter: CharacterAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var characters = mainViewModel.getCharacters()
        if(characters == null){
            characters = readJsonFileToList("got.json")
        }
        setContentView(R.layout.activity_main)

        listRecyclerView!!.layoutManager = GridLayoutManager(this, 2)

        adapter = CharacterAdapter(characters) {
            val intent = Intent(this, CharacterDetailsActivity::class.java)
            //intent.putExtra(CharacterDetailsActivity.CHARACTER, it)
            startActivity(intent)
        }
        listRecyclerView!!.adapter = adapter

    }

    fun readJsonFileToList(filePath: String): List<CharacterEntity> {
        val file = File(filePath)
        val json = assets.open(filePath).bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<CharacterEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }

    fun convertFlowToList(flow: Flow<List<CharacterEntity>>): List<CharacterEntity> {
        var characterList: List<CharacterEntity> = emptyList()

        GlobalScope.launch(Dispatchers.Default) {
            flow.collect{characters ->
                characterList = characters
            }
        }
        return characterList
    }


    fun createDatabase(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "GOT"
        ).allowMainThreadQueries().build()
    }

}