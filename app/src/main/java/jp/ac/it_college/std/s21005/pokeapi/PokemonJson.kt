package jp.ac.it_college.std.s21005.pokeapi

data class Pokemon(
    val id: Int,
    val name: String,
)
data class PokemonJson (
    val pokemon: List<Pokemon>
    )

data class Element(
    val id: Int,
    val name: String,
)