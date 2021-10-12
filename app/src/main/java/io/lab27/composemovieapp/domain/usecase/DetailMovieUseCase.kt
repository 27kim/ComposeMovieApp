package io.lab27.composemovieapp.domain.usecase

import io.lab27.composemovieapp.common.Resource
import io.lab27.composemovieapp.data.remote.dto.toMovieDetail
import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.composemovieapp.domain.model.MovieDetail
import io.lab27.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DetailMovieUseCase(private val repository: MovieRepository) {

    operator fun invoke(movie: Movie): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.fetchDetailMovie(movie.id.toString()).toMovieDetail()
            emit(Resource.Success(movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<MovieDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<MovieDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}