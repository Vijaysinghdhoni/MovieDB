package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.movies

sealed class MovieScreenEvent {

    data object GetMovies : MovieScreenEvent()

}