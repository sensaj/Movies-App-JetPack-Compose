package com.sensaj.moviesapp.data.remote

import com.sensaj.moviesapp.data.remote.response.Characters
import retrofit2.http.GET

interface Apis {
    @GET("characters")
    suspend fun getCharacters():Characters
}