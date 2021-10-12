package io.lab27.composemovieapp.presentation.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.composemovieapp.domain.usecase.QueryFavoritesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MyViewModel(
    val queryFavoritesUseCase: QueryFavoritesUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getMovies()
        }
    }
    private val _list = MutableLiveData<List<Movie>>()
    val list: LiveData<List<Movie>>
        get() = _list

    private suspend fun getMovies(){
        queryFavoritesUseCase().onEach {
            _list.value = it
        }.launchIn(viewModelScope)
    }
}