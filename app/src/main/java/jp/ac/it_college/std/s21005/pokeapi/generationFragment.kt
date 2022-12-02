package jp.ac.it_college.std.s21005.pokeapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.gson.Gson
import jp.ac.it_college.std.s21005.pokeapi.databinding.FragmentGenerationBinding

class generationFragment : Fragment() {
    private var _binding: FragmentGenerationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenerationBinding.inflate(inflater, container, false)
        val element = pokedexJson

        binding.onebtn.setOnClickListener {
            //val num = String
            val list = element.pokedex[0].entries.map { e -> e.pokemon_id }.toIntArray()
            //val list2 = arrayOf(pokemonJson.pokemon[0].id).map { e -> e. }
            //val list = pokemonJson.pokemon.filter { }.random().id
            //val array = arrayListOf(pokemonJson)
            //val jsonString =
            //val element = Gson().fromJson(jsonString)
            Navigation.findNavController(it).navigate(
                generationFragmentDirections.actionGenerationFragmentToQuestionFragment(list)
        )
    }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}