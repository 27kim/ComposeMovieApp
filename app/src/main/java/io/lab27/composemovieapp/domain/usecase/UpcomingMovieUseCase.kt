package io.lab27.composemovieapp.domain.usecase

import io.lab27.composemovieapp.common.Resource
import io.lab27.composemovieapp.data.remote.dto.toMovie
import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class UpcomingMovieUseCase(private val repository : MovieRepository) {

    operator fun invoke() : Flow<Resource<List<Movie>>> = flow{
        try{
            emit(Resource.Loading())
            val movies = repository.fetchUpcomingMovies().results.map {it.toMovie()}
            emit(Resource.Success(movies))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Movie>>("Couldn't reach server. Check your internet connection."))
        }
    }
}