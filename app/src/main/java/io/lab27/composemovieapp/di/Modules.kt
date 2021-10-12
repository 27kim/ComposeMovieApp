package io.lab27.composemovieapp.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import io.lab27.composemovieapp.data.local.MovieDatabase
import io.lab27.composemovieapp.data.remote.network.Client
import io.lab27.composemovieapp.data.repository.MovieRepositoryImpl
import io.lab27.composemovieapp.domain.usecase.*
import io.lab27.composemovieapp.presentation.detail.DetailViewModel
import io.lab27.composemovieapp.presentation.movie.MovieViewModel
import io.lab27.composemovieapp.presentation.my.MyViewModel
import io.lab27.movieapp.domain.repository.MovieRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val utilModule = module {
    single { GsonBuilder().setPrettyPrinting().create() }
}

val localDataModule = module {
    fun provideDatabase(application: Application) = Room
        .databaseBuilder(application, MovieDatabase::class.java, "movies")
        .fallbackToDestructiveMigration()
        .build()


    fun provideMovieDao(database: MovieDatabase) = database.movieDao

    single { provideDatabase(get()) }
    single { provideMovieDao(get()) }
}

val remoteDataModule = module {
    single { Client(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get(), get(), get()) }
    viewModel { MyViewModel(get()) }
    viewModel { DetailViewModel(get(), get(), get(), get()) }
}

val useCaseModule = module {
    single { PopularMovieUseCase(get()) }
    single { TopRatedMovieUseCase(get()) }
    single { UpcomingMovieUseCase(get()) }
    single { QueryFavoriteUseCase(get()) }
    single { QueryFavoritesUseCase(get()) }
    single { InsertMovieUseCase(get()) }
    single { DeleteMovieUseCase(get()) }
    single { DetailMovieUseCase(get()) }
}

val applicationModule = listOf(
    utilModule,
    localDataModule,
    remoteDataModule,
    viewModelModule,
    useCaseModule
)