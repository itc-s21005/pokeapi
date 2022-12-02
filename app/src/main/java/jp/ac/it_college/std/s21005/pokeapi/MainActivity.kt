package jp.ac.it_college.std.s21005.pokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import jp.ac.it_college.std.s21005.pokeapi.databinding.ActivityMainBinding
import java.io.InputStreamReader

lateinit var pokemonJson: PokemonJson
lateinit var pokedexJson: PokemondexJson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = getString(R.string.title)
        val inputStream = assets?.open("pokemon.json")
        val jsonReader = InputStreamReader(inputStream, "UTF-8").readText()
        val element = Gson().fromJson(jsonReader, PokemonJson::class.java)
        pokemonJson = element
        val inputStreamdex = assets?.open("pokedex.json")
        val jsonReaderdex = InputStreamReader(inputStreamdex, "UTF-8").readText()
        val elementdex = Gson().fromJson(jsonReaderdex, PokemondexJson::class.java)
        pokedexJson = elementdex
    }
}