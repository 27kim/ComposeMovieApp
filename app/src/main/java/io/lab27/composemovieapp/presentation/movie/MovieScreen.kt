package io.lab27.composemovieapp.presentation.movie

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import io.lab27.composemovieapp.R
import io.lab27.composemovieapp.common.ImageUrl
import io.lab27.composemovieapp.domain.model.Movie
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieScreen() {
    val movieViewModel = getViewModel<MovieViewModel>()
    val popular: List<Movie> by movieViewModel.popularMovies.observeAsState(emptyList())
    val topRated: List<Movie> by movieViewModel.topRatedMovies.observeAsState(emptyList())
    val upcoming: List<Movie> by movieViewModel.upcomingMovies.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {
        MovieRow(R.string.popular_title, R.string.popular_desc, popular)
        MovieRow(R.string.top_rated_title, R.string.top_rated_desc, topRated)
        MovieRow(R.string.upcoming_title, R.string.upcoming_desc, upcoming)
    }
}

@Composable
fun MovieRow(@StringRes title: Int, @StringRes desc: Int, movies: List<Movie>) {
    Text(text = stringResource(id = title))
    Text(text = stringResource(id = desc))
    if (movies.isNotEmpty()) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(movies) {
                MovieItem(movie = it)
            }
        }
    } else {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter("$ImageUrl${movie.poster_path}"),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(text = movie.title)
        Text(text = movie.release_date)
    }
}