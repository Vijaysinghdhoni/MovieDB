package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.tvshows

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling.RemoteDataSourceException
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.ErrorView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.HorizontalPagerShow
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.LazyRowShow
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.LoadingView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.getError
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.hasItems
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.isAnyError
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.isAnyRefreshing

@SuppressLint("UnrememberedMutableState")
@Composable
fun TvShowScreen(
    viewModel: TvShowViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {

    val tvShowState = viewModel.tvShowState

    val popularTvShowsLazyItems = tvShowState.popularTvShows.collectAsLazyPagingItems()
    val onAirTvShows = tvShowState.onAirTvShows.collectAsLazyPagingItems()
    val airingTodayTvShows = tvShowState.airingTodayTvShows.collectAsLazyPagingItems()
    val topRatedTvShows = tvShowState.topRatedTvShows.collectAsLazyPagingItems()

    val scrollState = rememberScrollState()

    val movieItems = listOf(
        popularTvShowsLazyItems,
        onAirTvShows,
        airingTodayTvShows,
        topRatedTvShows
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
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(scrollState)
            ) {

                HorizontalPagerShow(
                    list = airingTodayTvShows,
                    labelText = "UpComing Tv Shows",
                    onClick = { id ->
                        onItemClick(id)
                    }
                )

                LazyRowShow(
                    labelText = "Popular",
                    list = popularTvShowsLazyItems,
                    onClick = { id ->
                        onItemClick(id)
                    }
                )

                LazyRowShow(
                    labelText = "Top Rated",
                    list = topRatedTvShows,
                    onClick = { id ->
                        onItemClick(id)
                    }
                )


                LazyRowShow(
                    labelText = "on Air Tv Shows",
                    list = onAirTvShows,
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
                    .background(MaterialTheme.colorScheme.background)
            )

        }

        isLoadingError.first -> {
            val loadStateError = isLoadingError.second
            val error =
                (loadStateError?.error as RemoteDataSourceException).getError(LocalContext.current)
            ErrorView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                errorMssg = error,
                onRetryClick = {
                    viewModel.onEvent(TvShowScreenEvent.GetTvShows)
                }
            )
        }

    }

}