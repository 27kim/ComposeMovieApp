package io.lab27.composemovieapp.data.repository

import io.lab27.composemovieapp.data.local.MovieDao
import io.lab27.composemovieapp.data.remote.network.Client
import io.lab27.composemovieapp.domain.model.Movie
import io.lab27.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val client: Client, private val dao: MovieDao) : MovieRepository {
    override suspend fun fetchPopularMovies() = client.tmdbApi.getPopularMovies()
    override suspend fun fetchTopRatedMovies() = client.tmdbApi.getTopRatedMovies()
    override suspend fun fetchUpcomingMovies() = client.tmdbApi.getUpcomingMovies()
    override suspend fun fetchDetailMovie(id: String) = client.tmdbApi.getDetailMovie(id)

    override fun queryMovies(): Flow<List<Movie>> = dao.getMovies()
    override suspend fun queryMovie(movie: Movie) = dao.getMovieById(movie.id)
    override suspend fun insertMovie(movie: Movie) = dao.insertMovie(movie)
    override suspend fun deleteMovie(movie: Movie) = dao.deleteMovie(movie)
}