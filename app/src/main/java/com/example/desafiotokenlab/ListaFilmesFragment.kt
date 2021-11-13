package com.example.desafiotokenlab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiotokenlab.databinding.FragmentListaFilmesBinding

/**
 * Lista dos 20 melhores filmes de acordo com a TMDB.
 */
class ListaFilmesFragment : Fragment() {
    // TODO: substituir lista estática por lista de filmes da API.
    private val filmes = listOf(
        Filme(
            389,
            "12 Angry Men",
        ),
        Filme(
            244786,
            "Whiplash",
        ),
        Filme(
            13,
            "Forest Gump",
        ),
        Filme(
            510,
            "One Flew Over the Cuckoo's Nest",
        ),
        Filme(
            680,
            "Pulp Fiction",
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentListaFilmesBinding>(
            inflater,
            R.layout.fragment_lista_filmes,
            container,
            false
        )

        // Inicializa o recycler viewer
        val manager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        val listaFilmes: RecyclerView = binding.rvListaFilmes
        listaFilmes.apply {
            adapter = FilmeAdapter(filmes)
            layoutManager = manager
        }

        return binding.root
    }
}