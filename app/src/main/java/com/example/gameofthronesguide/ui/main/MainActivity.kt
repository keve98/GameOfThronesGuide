package com.example.gameofthronesguide.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gameofthronesguide.R
import com.example.gameofthronesguide.adapter.CharacterAdapter
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.persistence.CharacterDao
import com.example.gameofthronesguide.ui.details.CharacterDetailsActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.lifecycleScope

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private var adapter: CharacterAdapter?= null

    @Inject
    lateinit var characterDao: CharacterDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var characters : List<CharacterEntity>? = null
        lifecycleScope.launch{
            characters = mainViewModel.getCharacters()
        }

        if(characters == null){
            characters = readJsonFileToList("got.json")
        }
        setContentView(R.layout.activity_main)

        listRecyclerView!!.layoutManager = GridLayoutManager(this, 2)
        setupDatabase(characters!!)
        adapter = CharacterAdapter(characters!!) {
            val intent = Intent(this, CharacterDetailsActivity::class.java)
            intent.putExtra(CharacterDetailsActivity.CHARACTER, it.id.toString())
            startActivity(intent)
        }
        listRecyclerView!!.adapter = adapter

    }

    fun readJsonFileToList(filePath: String): List<CharacterEntity> {
        val json = assets.open(filePath).bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<CharacterEntity>>() {}.type
        return Gson().fromJson(json, listType)
    }

    fun setupDatabase(characters : List<CharacterEntity>){
        GlobalScope.launch {
            for(c in characters){
                characterDao.insertCharacter(c);
                print("INSERTED")
            }
        }

    }

}