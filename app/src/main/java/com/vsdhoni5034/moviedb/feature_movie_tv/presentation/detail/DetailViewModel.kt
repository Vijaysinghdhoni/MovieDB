package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Resource
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.DetailUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var detailState by mutableStateOf(DetailScreenState())
        private set

    private var detailID: Int? = null
    private var mediaType: String? = null

    init {
        detailID = savedStateHandle.get<Int>("detailID")
        mediaType = savedStateHandle.get<String>("detailMediaType")
        detailID?.let {
            if (it != -1) {
                getDetail(it, mediaType!!)
            }
        }
    }

    private fun getDetail(detailID: Int, mediaType: String) {

        when (mediaType) {

            Constants.MEDIA_TYPE_MOVIE -> {
                viewModelScope.launch {
                    getMoviesDetail(id = detailID)
                }
            }

            Constants.MEDIA_TYPE_TV -> {
                viewModelScope.launch {
                    getTvShowDetail(id = detailID)
                }
            }

        }

    }

    private suspend fun getMoviesDetail(id: Int) {
        detailUseCases.movieDetailUseCase(id)
            .collectLatest { response ->
                when (response) {

                    is Resource.Success -> {
                        Log.d("MyTag", "succes is coming viewModel")
                        detailState = detailState.copy(
                            detail = response.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        Log.d("MyTag", "error is viewModel")
                        detailState = detailState.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                }
            }
    }

    private suspend fun getTvShowDetail(id: Int) {
        detailUseCases.tvShowDetailUseCase(id).collectLatest { response ->
            when (response) {

                is Resource.Success -> {
                    Log.d("MyTag", "succes is coming viewModel")
                    detailState = detailState.copy(
                        detail = response.data,
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    Log.d("MyTag", "error is viewModel")
                    detailState = detailState.copy(
                        isLoading = false,
                        error = response.message
                    )
                }
            }
        }
    }

}