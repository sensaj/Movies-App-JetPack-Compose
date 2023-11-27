package com.sensaj.moviesapp.repos

import com.sensaj.moviesapp.data.remote.Apis
import com.sensaj.moviesapp.data.remote.response.Characters
import com.sensaj.moviesapp.utils.Resource
import javax.inject.Inject


class CharactersRepository @Inject constructor(
    private  val api:Apis
) {
    suspend fun getCharacterList():Resource<Characters>{
        val response = try {
            api.getCharacters()
        }catch (exception:Exception){
            return Resource.Error("Unknown Error Occured")
        }
        return Resource.Success(response)
    }
}