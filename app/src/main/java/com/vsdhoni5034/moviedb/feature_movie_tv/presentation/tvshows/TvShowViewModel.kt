package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.tvshows

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.TvShowsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowsUseCases: TvShowsUseCases
) : ViewModel() {


    var tvShowState by mutableStateOf(TvShowScreenState())
        private set

    init {
        getTvShows()
    }

    fun onEvent(tvShowScreenEvent: TvShowScreenEvent){

        when(tvShowScreenEvent){

            is TvShowScreenEvent.GetTvShows -> {
                getTvShows()
            }

        }

    }

    private fun getTvShows() {
        tvShowState = tvShowState.copy(
            airingTodayTvShows = tvShowsUseCases.airingTodayTvShowsUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope),
            popularTvShows = tvShowsUseCases.popularTvShowsUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope),
            onAirTvShows = tvShowsUseCases.onAirTvShowsUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope),
            topRatedTvShows = tvShowsUseCases.topRatedTvShowsUseCase().distinctUntilChanged()
                .cachedIn(viewModelScope)
        )
    }

}