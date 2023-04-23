package com.example.gameofthronesguide.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gameofthronesguide.R
import com.example.gameofthronesguide.network.CharacterService


class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var characterService: CharacterService? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}