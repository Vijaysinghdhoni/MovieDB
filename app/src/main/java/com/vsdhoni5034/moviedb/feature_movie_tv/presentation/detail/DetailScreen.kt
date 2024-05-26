package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.ErrorView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.LoadingView
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents.TopAppBar
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail.components.DetailScreenContent
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.getError

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
            detail?.let {
                TopAppBar(
                    title = it.title,
                    scrollBehavior = scrollBehavior,
                    onNavigationUp = onNavigationUp
                )
            }
        }
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            when {
                isLoading -> LoadingView(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )

                error != null -> {
                    Log.d("MyTag", "error is : $error")
                    val msg = error.getError(LocalContext.current)
                    ErrorView(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        errorMssg = msg,
                        onRetryClick = {}
                    )
                }

                detail != null -> {
                    Log.d("MyTag", "succes is coming detailscreen")
                    DetailScreenContent(
                        detailUi = detail,
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    )
                }

                else -> {

                    ErrorView(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        errorMssg = "Unexpected error occurred. We are working on it.",
                        onRetryClick = {}
                    )
                }
            }
        }
    }
}