package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.ErrorView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.LoadingView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.TopAppBar
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail.components.DetailScreenContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigationUp: () -> Unit
) {
    val state = viewModel.detailState
    val detail = state.detail
    val isLoading = state.isLoading
    val error = state.error

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (detail != null) {
                TopAppBar(
                    title = detail.title,
                    scrollBehavior = scrollBehavior,
                    onNavigationUp = onNavigationUp
                )
            } else {
                TopAppBar(
                    title = "Detail Screen",
                    scrollBehavior = scrollBehavior,
                    onNavigationUp = onNavigationUp
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            when {
                isLoading -> LoadingView(Modifier.fillMaxSize())
                error != null -> {
                    Log.d("MyTag", "error is : $error")
                    ErrorView(
                        modifier = Modifier.fillMaxSize(),
                        onRetryClick = {}
                    )
                }

                detail != null -> {
                    Log.d("MyTag","succes is coming detailscreen")
                    DetailScreenContent(detailUi = detail)
                }

                else -> {
                    Log.d("MyTag", "error is coming")
                    ErrorView(
                        modifier = Modifier.fillMaxSize(),
                        onRetryClick = {}
                    )
                }
            }
        }
    }
}