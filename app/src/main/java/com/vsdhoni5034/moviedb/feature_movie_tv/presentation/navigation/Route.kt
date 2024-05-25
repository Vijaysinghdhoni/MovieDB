package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation

sealed class Route(val route: String) {


    data object MovieScreen : Route("MOVIE_SCREEN_ROUTE")

    data object TvShowsScreen : Route("TV_SHOWS_ROUTE")

    data object DetailScreen : Route("Detail_Screen_Route")

}