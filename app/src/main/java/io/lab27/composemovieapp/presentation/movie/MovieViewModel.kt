package io.lab27.composemovieapp.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.lab27.composemovieapp.common.Resource
import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.composemovieapp.domain.usecase.PopularMovieUseCase
import io.lab27.composemovieapp.domain.usecase.TopRatedMovieUseCase
import io.lab27.composemovieapp.domain.usecase.UpcomingMovieUseCase
import kotlinx.coroutines.flow.collectLatest

class MovieViewModel(
    private val popularMovieUseCase: PopularMovieUseCase,
    private val topRatedMovieUseCase: TopRatedMovieUseCase,
    private val upcomingMovieUseCase: UpcomingMovieUseCase
) : ViewModel() {

    val popularMovies: LiveData<List<Movie>> = liveData {
        popularMovieUseCase.invoke().collectLatest { movies ->
            movies.data?.let { emit(it) }
        }
    }

    val topRatedMovies: LiveData<List<Movie>> = liveData {
        topRatedMovieUseCase.invoke().collectLatest { movies ->
            movies.data?.let { emit(it) }
        }
    }

    val upcomingMovies: LiveData<List<Movie>> = liveData {
        upcomingMovieUseCase.invoke().collectLatest { movies ->
            movies.data?.let { emit(it) }
        }
    }
}
