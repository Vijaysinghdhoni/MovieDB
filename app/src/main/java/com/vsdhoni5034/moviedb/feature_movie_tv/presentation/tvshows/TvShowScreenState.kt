package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.tvshows

import androidx.paging.PagingData
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class TvShowScreenState(
    val airingTodayTvShows: Flow<PagingData<MediaHome>> = emptyFlow(),
    val onAirTvShows: Flow<PagingData<MediaHome>> = emptyFlow(),
    val popularTvShows: Flow<PagingData<MediaHome>> = emptyFlow(),
    val topRatedTvShows: Flow<PagingData<MediaHome>> = emptyFlow()
)
