package com.example.gameofthronesguide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gameofthronesguide.R
import com.example.gameofthronesguide.model.CharacterEntity
import kotlinx.android.synthetic.main.character_card.view.*

class CharacterAdapter(private val characters: List<CharacterEntity>, private val listener: (CharacterEntity) -> Unit) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.character_card))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(characters[position], listener)

    override fun getItemCount() = characters.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterEntity, listener: (CharacterEntity) -> Unit) = with(itemView) {
            character_name.text = item.fullName
            Glide.with(context)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .dontAnimate()
                .override(333, 500) // To allow parallax effect

            setOnClickListener { listener(item) }
        }
    }
}