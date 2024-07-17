package com.difawitsqard.godzillaapps.network

import com.difawitsqard.godzillaapps.model.Superhero
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("a973149af3780543eed7777f9eab5dff/287")
    fun getSuperhero(): Call<Superhero>
}