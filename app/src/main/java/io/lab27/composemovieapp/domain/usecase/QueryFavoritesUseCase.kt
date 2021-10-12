package io.lab27.composemovieapp.domain.usecase

import io.lab27.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.map

class QueryFavoritesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.queryMovies()
}