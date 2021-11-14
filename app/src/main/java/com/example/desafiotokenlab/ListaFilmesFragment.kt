package com.example.desafiotokenlab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiotokenlab.databinding.FragmentListaFilmesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Lista dos 20 melhores filmes de acordo com a TMDB.
 */
class ListaFilmesFragment : Fragment() {
    private lateinit var binding : FragmentListaFilmesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_lista_filmes,
            container,
            false
        )
        carregaFilmes()
        return binding.root
    }

    /**
     * Tenta carregar a lista de filmes usando a API do TMDB
     */
    private fun carregaFilmes() {
        TmdbApi.retrofitService.getFilmes().enqueue(
            object : Callback<List<Filme>> {
                override fun onResponse(call: Call<List<Filme>>, response: Response<List<Filme>>) {
                    if (!response.isSuccessful) {
                        val msg = if (response.code() >= 500)
                            "Falha na conexão com o servidor"
                        else
                            "Não foi possível obter a lista de filmes."
                        onCarregaFilmesFailure(msg)
                        return
                    }
                    val filmes = response.body()!!
                    val manager: RecyclerView.LayoutManager = LinearLayoutManager(context)
                    val listaFilmes: RecyclerView = binding.rvListaFilmes
                    listaFilmes.apply {
                        adapter = FilmeAdapter(filmes)
                        layoutManager = manager
                    }
                }

                override fun onFailure(call: Call<List<Filme>>, t: Throwable) {
                    val msg = "Sem conexão com a internet."
                    onCarregaFilmesFailure(msg)
                }
            })

    }

    /**
     * Notifica o usuário do motivo da falha de conexão.
     */
    private fun onCarregaFilmesFailure(msg: String) {
        Toast.makeText(
            activity,
            msg,
            Toast.LENGTH_SHORT
        ).show()
        view?.findNavController()?.navigate(R.id.action_listaFilmesFragment_to_listaFilmesTentarNovamente)
    }
}