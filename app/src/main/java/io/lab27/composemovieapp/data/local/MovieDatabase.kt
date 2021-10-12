package io.lab27.composemovieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.lab27.composemovieapp.domain.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        const val DATABASE_NAME = "movie_db"
    }
}