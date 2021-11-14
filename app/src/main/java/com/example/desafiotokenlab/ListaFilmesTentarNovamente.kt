package com.example.desafiotokenlab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.desafiotokenlab.databinding.FragmentListaFilmesTentarNovamenteBinding

class ListaFilmesTentarNovamente : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListaFilmesTentarNovamenteBinding>(
            inflater,
            R.layout.fragment_lista_filmes_tentar_novamente,
            container,
            false
        )
        // Setta o click listener para recarregar filmes
        binding.btTentarNovamente.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_listaFilmesTentarNovamente_to_listaFilmesFragment)
        }
        return binding.root
    }
}