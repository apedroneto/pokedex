package com.example.pokedex.config

import com.example.pokedex.PokeFoto
import com.example.pokedex.services.PokeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class RetrofitInitializer {

    private var retrofit = Retrofit.Builder()
        .baseUrl("localhost:3031")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun pokeService(): PokeService = retrofit.create(PokeService::class.java)


}