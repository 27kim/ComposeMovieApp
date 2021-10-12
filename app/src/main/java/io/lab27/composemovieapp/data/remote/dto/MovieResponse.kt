package io.lab27.composemovieapp.data.remote.dto

import io.lab27.composemovieapp.domain.model.MovieDetail

data class MovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)

data class UpcomingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDetailResponse(
    val adult: Boolean = false,
    val backdrop_path: String,
    val belongs_to_collection: Any?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountry> = emptyList(),
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailResponse.toMovieDetail(): MovieDetail = MovieDetail(
    adult = adult,
    backdrop_path = backdrop_path,
    belongs_to_collection = belongs_to_collection,
    budget = budget,
    genres = genres,
    homepage = homepage,
    id = id,
    imdb_id = imdb_id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    production_companies = production_companies,
    production_countries = production_countries,
    release_date = release_date,
    revenue = revenue,
    runtime = runtime,
    spoken_languages = spoken_languages,
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)

data class Dates(
    val maximum: String,
    val minimum: String
)