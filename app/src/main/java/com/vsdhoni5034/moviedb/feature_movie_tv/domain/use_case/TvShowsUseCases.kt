package com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case

data class TvShowsUseCases(
    val airingTodayTvShowsUseCase: AiringTodayTvShowsUseCase,
    val onAirTvShowsUseCase: OnAirTvShowsUseCase,
    val popularTvShowsUseCase: PopularTvShowsUseCase,
    val topRatedTvShowsUseCase: TopRatedTvShowsUseCase
)