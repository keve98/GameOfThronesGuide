package com.example.gameofthronesguide

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gameofthronesguide.model.CharacterEntity
import com.example.gameofthronesguide.persistence.AppDatabase
import com.example.gameofthronesguide.persistence.CharacterDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.Matchers.equalTo
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.gameofthronesguide", appContext.packageName)
    }

    private lateinit var characterDao: CharacterDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        characterDao = db.characterDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeCharacterAndReadIn() {
        val character = CharacterEntity(2, "Jon", "Snow", "Jon Snow", "King of the North", "House Stark", "jon-snow.jpg", "https://thronesapi.com/assets/images/jon-snow.jpg")
        GlobalScope.launch {
            characterDao.insertCharacter(character)
            val search = characterDao.getCharacter(2)
            assertThat(search, equalTo(character))
        }

    }
}