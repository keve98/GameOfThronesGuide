package com.example.gameofthronesguide.ui.details

import android.app.Activity
import android.os.Bundle
import android.provider.Settings.Global
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gameofthronesguide.R
import com.example.gameofthronesguide.di.PersistenceModule
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.persistence.AppDatabase
import com.example.gameofthronesguide.persistence.CharacterDao
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.character_card.imageURL
import kotlinx.coroutines.*
import javax.inject.Inject

class CharacterDetailsActivity : Activity() {
    lateinit var appDatabase: AppDatabase

    companion object {
        private val TAG = CharacterDetailsActivity::class.java.simpleName
        const val CHARACTER = "character"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        appDatabase = PersistenceModule.provideCharacterDatabase(applicationContext)
        //characterDao = PersistenceModule.provideCharacterDao(appDatabase)
        var id = intent.getStringExtra(CHARACTER)


        var character = PersistenceModule.provideCharacterDao(appDatabase).getCharacter(id!!.toLong())

        var options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round);
        Glide.with(this)
            .load(character?.imageUrl)
            .apply(options)
            .into(imageURL)

        firstName.text = character?.firstName
        lastName.text = character?.lastName
        fullName.text = character?.fullName
        family.text = character?.family
        title_.text = character?.title
        family.text = character?.family
    }
}