package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.movies

import androidx.paging.PagingData
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieScreenState(
    val upcomingMovies: Flow<PagingData<MediaHome>> = emptyFlow(),
    val popularMovies: Flow<PagingData<MediaHome>> = emptyFlow(),
    val topRatedMovies: Flow<PagingData<MediaHome>> = emptyFlow(),
    val nowPlayingMovies: Flow<PagingData<MediaHome>> = emptyFlow()
)
