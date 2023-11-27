package com.sensaj.moviesapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sensaj.moviesapp.repos.CharactersRepository
import com.sensaj.moviesapp.data.remote.response.Character
import com.sensaj.moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharactersRepository
):ViewModel(){

    var characters = mutableStateOf<List<Character>>(listOf())
    var loadingError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var cachedCharacterList= listOf<Character>()
    private var isSearchStarted = true
    private var isSearching = mutableStateOf(false)

    init {
        loadCharacters()
    }
    fun searchMovieCharacters(query:String){
        val listToSearch = if (isSearchStarted){
            characters.value
        }else{
            cachedCharacterList
        }

        viewModelScope.launch(Dispatchers.Default) {
                if (query.isEmpty()){
                    characters.value = cachedCharacterList
                    isSearching.value =false
                    isSearchStarted = false
                    return@launch
                }


                val results = listToSearch.filter {character->
                    character.name.contains(query.trim(),ignoreCase = true)
                            || character.actor.contains(query.trim(),ignoreCase = true)
                }

                if (isSearchStarted){
                    cachedCharacterList = characters.value
                    isSearchStarted = false
                }
                characters.value = results
                isSearching.value =true

        }
    }
    fun loadCharacters(){
            viewModelScope.launch {
                isLoading.value = true
                loadingError.value = ""
                val response = repository.getCharacterList()

                when(response){
                    is Resource.Success->{
                        characters.value += response.data!!
                        isLoading.value = false
                    }
                    is Resource.Error->{
                        loadingError.value = "${response.message}"
                        isLoading.value=false
                    }
                    else -> {
                        isLoading.value = false
                    }
                }
            }
    }

}