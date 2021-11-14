package com.example.desafiotokenlab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.desafiotokenlab.databinding.FragmentDetalhesFilmeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalhesFilmeFragment : Fragment() {
    private lateinit var binding: FragmentDetalhesFilmeBinding
    private val args: DetalhesFilmeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detalhes_filme,
            container,
            false
        )
        carregaDetalhes(args.id)
        return binding.root
    }

    /**
     * Tenta carregar os detalhes do filme usando a API
     */
    private fun carregaDetalhes(id: Int) {
        TmdbApi.retrofitService.getDetalhesFilme(id).enqueue(
            object : Callback<DetalhesFilme> {
                override fun onResponse(
                    call: Call<DetalhesFilme>,
                    response: Response<DetalhesFilme>
                ) {
                    if (!response.isSuccessful) {
                        val msg = if (response.code() >= 500)
                            "Falha na conexão com o servidor"
                        else
                            "Não foi possível obter os detalhes do filme."
                        onCarregaDetalhesFailure(msg)
                    }
                    val detalhes = response.body()!!
                    // Carrega informações textuais
                    binding.tvTitle.text = detalhes.titulo
                    binding.tvResumo.text = detalhes.resumo
                    binding.tvDataLancamento.text = detalhes.dataLancamento
                    // Carrega o poster
                    Glide.with(this@DetalhesFilmeFragment)
                        .load(detalhes.posterUrl)
                        .into(binding.ivPoster)
                    binding.ivPoster.contentDescription = "Poster de ${detalhes.titulo}"
                }

                override fun onFailure(call: Call<DetalhesFilme>, t: Throwable) {
                    val msg = "Sem conexão com a internet."
                    onCarregaDetalhesFailure(msg)
                }
            })

    }

    /**
     * Lida com falhas de conexão no carregamento dos detalhes de um filme.
     */
    private fun onCarregaDetalhesFailure(msg: String) {
        // TODO: adicionar tela para voltar ao menu anterior
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

