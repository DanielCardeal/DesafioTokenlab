package com.example.desafiotokenlab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Um RecyclerView Adapter para filmes.
 */
class FilmeAdapter(private val filmes: List<Filme>) :
    RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    class FilmeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        /**
         * Associa os valores armazenados em filme à elemtos de UI da view *filme_thumbnail_layout*.
         */
        fun bind(filme: Filme) {
            val tvTitulo = view.findViewById<TextView>(R.id.tvTitulo)
            tvTitulo.text = filme.titulo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context) .inflate(R.layout.filme_thumbnail_layout, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.bind(filmes[position])
    }

    override fun getItemCount(): Int {
        return filmes.size
    }
}
