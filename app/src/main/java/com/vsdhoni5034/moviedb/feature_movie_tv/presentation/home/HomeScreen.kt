package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vsdhoni5034.moviedb.R
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.TopAppBar
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation.BottomNavigationItem
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation.NavGraph
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController()
) {

    val navItems =
        listOf(
            BottomNavigationItem(
                title = Route.MovieScreen.route,
                selectedIcon = painterResource(id = R.drawable.movies_selected),
                unSelectedIcon = painterResource(id = R.drawable.movie_unselected)
            ),
            BottomNavigationItem(
                title = Route.TvShowsScreen.route,
                selectedIcon = painterResource(id = R.drawable.tv_selected),
                unSelectedIcon = painterResource(id = R.drawable.tv_unselected)
            )
        )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showNavigationState = rememberSaveable { (mutableStateOf(false)) }
    showNavigationState.value = navItems.any { it.title == currentRoute }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = showNavigationState.value,
                enter = slideInVertically(
                    animationSpec = tween(300)
                ) {
                    -it
                },
                exit = slideOutVertically(
                    animationSpec = tween(300)
                ) {
                    -it
                }
            ) {

                TopAppBar(
                    title = "Movies Player",
                    onSettingClick = {

                    }
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = showNavigationState.value,
                enter = slideInVertically(
                    animationSpec = tween(300)
                ) {
                    it
                },
                exit = slideOutVertically(
                    animationSpec = tween(300)
                ) {
                    it
                }
            ) {

                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        8.dp
                    )
                ) {
                    navItems.forEach { bottomNavigationItem ->
                        NavigationBarItem(selected = currentRoute == bottomNavigationItem.title,
                            onClick = {
                                navController.navigate(bottomNavigationItem.title) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = if (currentRoute == bottomNavigationItem.title) {
                                        bottomNavigationItem.selectedIcon
                                    } else {
                                        bottomNavigationItem.unSelectedIcon
                                    }, contentDescription = null
                                )
                            }
                        )

                    }

                }


            }
        }
    ) { paddingValue ->
        Box(modifier = Modifier.padding(paddingValue)) {
            NavGraph(navController = navController)
        }
    }


}