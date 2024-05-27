package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation

import android.icu.text.CaseMap.Title
import kotlinx.serialization.Serializable


sealed class Route(val route: String) {


    data object MovieScreen : Route("Movie Screen")


    data object TvShowsScreen : Route("Tv Show Screen")


}

@Serializable
data class DetailScreen(
    val detailType: String,
    val detailID: String
)