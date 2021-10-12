package io.lab27.composemovieapp.data.remote

import io.lab27.composemovieapp.data.remote.dto.MovieDetailResponse
import io.lab27.composemovieapp.data.remote.dto.MovieResponse
import io.lab27.composemovieapp.data.remote.dto.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(): UpcomingResponse

    @GET("/3/movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id : String
    ): MovieDetailResponse
}