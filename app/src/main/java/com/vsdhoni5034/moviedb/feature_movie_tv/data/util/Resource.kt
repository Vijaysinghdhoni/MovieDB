package com.vsdhoni5034.moviedb.feature_movie_tv.data.util

import com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling.RemoteDataSourceException

sealed class Resource<T>(
    val data: T? = null,
    val remoteDataSourceException: RemoteDataSourceException? = null
) {

    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(remoteDataSourceException: RemoteDataSourceException, data: T? = null) :
        Resource<T>(data, remoteDataSourceException)


}