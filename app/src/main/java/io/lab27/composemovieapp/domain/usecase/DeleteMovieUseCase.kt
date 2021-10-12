package io.lab27.composemovieapp.domain.usecase

import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.movieapp.domain.repository.MovieRepository

class DeleteMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.deleteMovie(movie)
    }
}