package jp.ac.it_college.std.s21005.pokeapi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    val sprites: Sprites,
    val name: String,
)

@JsonClass(generateAdapter = true)
data class Sprites(
    val other: Other
)

@JsonClass(generateAdapter = true)
data class Other(
    @Json(name = "official-artwork")val officialArtwork: OfficialArtwork
)

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    @Json(name = "front_default")val frontDefault: String
)