package jp.ac.it_college.std.s21005.pokeapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonServise {
    @GET("pokemon/{id}/")
    fun fetchPokemon(@Path("id") id: Int): Call<PokemonInfo>
}