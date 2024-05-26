package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail

import com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling.RemoteDataSourceException
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi

data class DetailScreenState(
    val detail: DetailUi? = null,
    val isLoading: Boolean = true,
    val error: RemoteDataSourceException? = null
)
