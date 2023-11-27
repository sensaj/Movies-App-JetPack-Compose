package com.sensaj.moviesapp.data.remote

import com.sensaj.moviesapp.repos.CharactersRepository
import com.sensaj.moviesapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun provideCharactersRepository(
        apis: Apis
    )=CharactersRepository(apis)

    @Singleton
    @Provides
    fun provideAppApi():Apis{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Apis::class.java)
    }
}