package com.daggoth.pruebaopenbank.ui.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daggoth.pruebaopenbank.R
import com.daggoth.pruebaopenbank.data.model.Results
import com.squareup.picasso.Picasso

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var charactersName: TextView = itemView.findViewById(R.id.characters_name)
    var charactersThumbnail: ImageView = itemView.findViewById(R.id.characters_thumbnail)

    fun bind(context: Context, result: Results) {
        charactersName.text = result.name
        Picasso.with(context).load(result.getImageUrl()).into(charactersThumbnail)
    }
}