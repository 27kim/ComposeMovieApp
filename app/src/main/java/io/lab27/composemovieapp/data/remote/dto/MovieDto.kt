package io.lab27.composemovieapp.data.remote.dto

import android.os.Parcelable
import io.lab27.composemovieapp.domain.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String,
//    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable

fun MovieDto.toMovie() = Movie(
    id = id,
    adult = adult,
//    genre_ids = genre_ids,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)
