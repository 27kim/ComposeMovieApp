package io.lab27.composemovieapp.data.local

import androidx.room.*
import io.lab27.composemovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Flow<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Update
    suspend fun updateFavorite(movie : Movie)

}