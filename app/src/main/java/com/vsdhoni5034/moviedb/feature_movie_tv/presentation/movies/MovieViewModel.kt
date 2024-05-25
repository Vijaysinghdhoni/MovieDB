package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    var movieState by mutableStateOf(MovieScreenState())
        private set


    init {
        getMovies()
    }

    fun onEvent(movieScreenEvent: MovieScreenEvent){

        when(movieScreenEvent){
            is MovieScreenEvent.GetMovies -> {
                getMovies()
            }
        }

    }

    private fun getMovies() {

        movieState = movieState.copy(
            upcomingMovies = moviesUseCases.upComingMoviesUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope),
            topRatedMovies = moviesUseCases.topRatedMoviesUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope),
            nowPlayingMovies = moviesUseCases.nowPlayingMoviesUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope),
            popularMovies = moviesUseCases.popularMoviesUseCases().distinctUntilChanged()
                .cachedIn(viewModelScope)
        )

    }
}