package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.tvshows

sealed class TvShowScreenEvent {

    data object GetTvShows : TvShowScreenEvent()

}