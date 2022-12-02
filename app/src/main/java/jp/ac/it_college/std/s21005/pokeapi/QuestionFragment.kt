package jp.ac.it_college.std.s21005.pokeapi

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import jp.ac.it_college.std.s21005.pokeapi.databinding.FragmentQuestionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class QuestionFragment : Fragment() {
    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val baseurl = "https://pokeapi.co/api/v2/"
    val args:QuestionFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater,container, false)
        binding.pokeimg.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
        val num = args.pokemonid.random()
        showPokemon(num)
        val Count = args.qCount
        binding.tvcount.text = getString(R.string.m_count, Count)
        val list = listOf(
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4
        ).shuffled()

        list[0].text = pokemonJson.pokemon.filter { p -> p.id == num } [0].name
        list[1].text = pokemonJson.pokemon.filter { p -> p.id != num }.random().name
        list[2].text = pokemonJson.pokemon.filter { p -> p.id != num }.random().name
        list[3].text = pokemonJson.pokemon.filter { p -> p.id != num }.random().name

        class ClickListener(val right: Boolean) : View.OnClickListener{
            override fun onClick(v: View) {
                if(right) {
                    Toast.makeText(context,"正解○", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,"不正解☓", Toast.LENGTH_SHORT).show()
                }
                if (Count < 10) {
                    Navigation.findNavController(v).navigate(
                        QuestionFragmentDirections.actionQuestionFragmentSelf2(
                                args.pokemonid
                        ).apply {
                            setQCount(Count + 1)
                        }
                    )
                }else{
                    Navigation.findNavController(v).navigate(
                        QuestionFragmentDirections.actionQuestionFragmentToResultFragment()
                    )
                }
            }
        }
        list[0].setOnClickListener(ClickListener(true))
        list[1].setOnClickListener(ClickListener(false))
        list[2].setOnClickListener(ClickListener(false))
        list[3].setOnClickListener(ClickListener(false))
        return binding.root
    }

    @UiThread
    private fun showPokemon(id: Int) {
        lifecycleScope.launch {
            val info = getPokemonInfo(id)
            val url = info.sprites.other.officialArtwork.frontDefault
            Picasso.get().load(url).into(binding.pokeimg)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @WorkerThread
    private suspend fun getPokemonInfo(id: Int): PokemonInfo {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder().apply {
                baseUrl(baseurl)
                addConverterFactory(MoshiConverterFactory.create(moshi))
            }.build()
            val service: PokemonServise = retrofit.create(PokemonServise::class.java)
            service.fetchPokemon(id).execute().body() ?: throw IllegalStateException("ポケモンが見つかりません")
        }
    }
}
