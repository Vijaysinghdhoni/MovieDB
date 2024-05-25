package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail.DetailScreen
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.movies.MovieScreen
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.tvshows.TvShowScreen
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.Constants

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Route.MovieScreen.route
    ) {

        composable(route = Route.MovieScreen.route) {
            MovieScreen(
                onItemClick = { id ->
                    navController.navigate("${Route.DetailScreen.route}/$id/${Constants.MEDIA_TYPE_MOVIE}")
                }
            )
        }

        composable(route = Route.TvShowsScreen.route) {
            TvShowScreen(
                onItemClick = { id ->
                    navController.navigate("${Route.DetailScreen.route}/$id/${Constants.MEDIA_TYPE_TV}")
                }
            )
        }

        composable(
            route = "${Route.DetailScreen.route}/{detailID}/{detailMediaType}",
            arguments = listOf(
                navArgument("detailID") {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument("detailMediaType") { type = NavType.StringType })
        ) {

            DetailScreen(
                onNavigationUp = {
                    navController.popBackStack()
                }
            )
        }
    }

}