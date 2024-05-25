package com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case

data class MoviesUseCases(
    val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    val popularMoviesUseCases: PopularMoviesUseCases,
    val upComingMoviesUseCase: UpComingMoviesUseCase
)