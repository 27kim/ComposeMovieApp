package io.lab27.composemovieapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.composemovieapp.domain.model.MovieDetail
import io.lab27.composemovieapp.domain.usecase.DeleteMovieUseCase
import io.lab27.composemovieapp.domain.usecase.DetailMovieUseCase
import io.lab27.composemovieapp.domain.usecase.InsertMovieUseCase
import io.lab27.composemovieapp.domain.usecase.QueryFavoriteUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailMovieUseCase: DetailMovieUseCase,
    val insertMovieUseCase: InsertMovieUseCase,
    val deleteMovieUseCase: DeleteMovieUseCase,
    val queryFavoriteUseCase: QueryFavoriteUseCase

) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail>
        get() = _movieDetail

    private val _favorite = MutableLiveData(false)
    val favorite : LiveData<Boolean>
        get()= _favorite

    fun fetchDetail(movie: Movie) {
        viewModelScope.launch {

            launch {
                queryFavoriteUseCase(movie).collectLatest {
                    it?.let{
                        _favorite.value = true
                    }
                }
            }

            detailMovieUseCase.invoke(movie).collectLatest { movieDetail ->
                movieDetail.data?.let {
                    _movieDetail.value = it
                }
            }
        }
    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch {
            if (_favorite.value == false) {
                insertMovieUseCase(movie)
                _favorite.value = true
            } else {
                deleteMovieUseCase(movie)
                _favorite.value = false
            }
        }
    }
}