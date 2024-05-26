package com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling

import com.vsdhoni5034.moviedb.R
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

object RequestErrorHandler {

    private const val HTTP_CODE_CLIENT_START = 400
    private const val HTTP_CODE_CLIENT_END = 499
    private const val HTTP_CODE_SERVER_START = 500
    private const val HTTP_CODE_SERVER_END = 599


    fun getRequestedError(throwable: Throwable): RemoteDataSourceException {

        return when (throwable) {

            is HttpException -> {
                getHttpException(throwable)
            }

            is SocketTimeoutException -> {
                RemoteDataSourceException.Timeout(R.string.error_timeout_message)
            }

            is IOException -> {
                RemoteDataSourceException.Timeout(R.string.error_network)
            }

            else -> {
                RemoteDataSourceException.Timeout(R.string.error_unexpected_message)
            }

        }

    }

    private fun getHttpException(httpException: HttpException): RemoteDataSourceException {

        return when (httpException.code()) {

            in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {
                RemoteDataSourceException.Client(R.string.error_client_unexpected_message)
            }

            in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {
                RemoteDataSourceException.Client(R.string.error_server_unexpected_message)
            }

            else -> {
                RemoteDataSourceException.Client(R.string.error_unexpected_message)
            }

        }

    }

}