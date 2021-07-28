package com.example.mycalculatorclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordzAdapter(var words: Array<String>) :RecyclerView.Adapter<WordzAdapter.WordVH>(){
    class WordVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.tvRvItem)
        fun bind(word: String) {
            wordItemView.text = word
        }

        companion object {
            fun create(parent: ViewGroup): WordVH {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
                return WordVH(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordVH {
        return WordVH.create(parent)
    }

    override fun onBindViewHolder(holder: WordVH, position: Int) {
        holder.bind(words[position])
    }

    override fun getItemCount(): Int {
        return words.size
    }

}
