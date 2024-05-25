package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.ErrorView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.HorizontalPagerShow
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.LazyRowShow
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.LoadingView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.hasItems
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.isAnyError
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.isAnyRefreshing

@SuppressLint("UnrememberedMutableState")
@Composable
fun MovieScreen(
    movieViewModel: MovieViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {

    val movieState = movieViewModel.movieState

    val popularMoviesLazyItems = movieState.popularMovies.collectAsLazyPagingItems()
    val upcomingMoviesLazyItems = movieState.upcomingMovies.collectAsLazyPagingItems()
    val nowPlayingMoviesLazyItems = movieState.nowPlayingMovies.collectAsLazyPagingItems()
    val topRatedMoviesLazyItems = movieState.topRatedMovies.collectAsLazyPagingItems()

    val scrollState = rememberScrollState()

    val movieItems = listOf(
        popularMoviesLazyItems,
        upcomingMoviesLazyItems,
        nowPlayingMoviesLazyItems,
        topRatedMoviesLazyItems
    )

    val isScreenLoading by derivedStateOf {
        movieItems.isAnyRefreshing()
    }

    val hasItems by derivedStateOf {
        movieItems.hasItems()
    }

    val isLoadingError by derivedStateOf {
        movieItems.isAnyError()
    }

    when {

        hasItems -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                HorizontalPagerShow(
                    list = upcomingMoviesLazyItems,
                    labelText = "UpComing Movies",
                    onClick = { movieId ->
                        onItemClick(movieId)
                    }
                )

                LazyRowShow(
                    labelText = "Now Playing",
                    list = nowPlayingMoviesLazyItems,
                    onClick = { id ->
                        onItemClick(id)
                    }
                )

                LazyRowShow(
                    labelText = "Popular",
                    list = popularMoviesLazyItems,
                    onClick = { id ->
                        onItemClick(id)
                    }
                )


                LazyRowShow(
                    labelText = "Top Rated Movies",
                    list = topRatedMoviesLazyItems,
                    onClick = { id ->
                        onItemClick(id)
                    }
                )

            }


        }

        isScreenLoading -> {

            LoadingView(
                modifier = Modifier
                    .fillMaxSize()
            )

        }

        isLoadingError.first -> {
            ErrorView(
                modifier = Modifier.fillMaxSize(),
                onRetryClick = {
                    movieViewModel.onEvent(MovieScreenEvent.GetMovies)
                }
            )
        }

    }

}