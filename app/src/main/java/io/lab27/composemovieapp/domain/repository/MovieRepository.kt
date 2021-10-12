package io.lab27.movieapp.domain.repository

import io.lab27.composemovieapp.data.remote.dto.MovieDetailResponse
import io.lab27.composemovieapp.data.remote.dto.MovieResponse
import io.lab27.composemovieapp.data.remote.dto.UpcomingResponse
import io.lab27.composemovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchPopularMovies() : MovieResponse
    suspend fun fetchTopRatedMovies() : MovieResponse
    suspend fun fetchUpcomingMovies() : UpcomingResponse
    suspend fun fetchDetailMovie(id : String) : MovieDetailResponse

    fun queryMovies() : Flow<List<Movie>>
    suspend fun queryMovie(movie : Movie) : Flow<Movie>
    suspend fun insertMovie(movie : Movie)
    suspend fun deleteMovie(movie : Movie)
}