package com.example.pokedex.services

import com.example.pokedex.PokeFoto
import com.example.pokedex.PokeResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface PokeService {

    @POST("image-analise")
    fun sendImage(@Body pokefoto: PokeFoto) : Call<PokeResult>

}
