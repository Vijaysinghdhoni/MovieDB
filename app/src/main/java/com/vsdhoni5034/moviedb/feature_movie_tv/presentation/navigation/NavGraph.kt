package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                    navController.navigate(
                        DetailScreen(
                            detailType = Constants.MEDIA_TYPE_MOVIE,
                            detailID = id.toString()
                        )
                    )
                }
            )
        }

        composable(route = Route.TvShowsScreen.route) {
            TvShowScreen(
                onItemClick = { id ->
                    navController.navigate(
                        DetailScreen(
                            detailType = Constants.MEDIA_TYPE_TV,
                            detailID = id.toString()
                        )
                    )
                }
            )
        }

        composable<DetailScreen> {

            DetailScreen(
                onNavigationUp = {
                    navController.popBackStack()
                }
            )
        }
    }

}