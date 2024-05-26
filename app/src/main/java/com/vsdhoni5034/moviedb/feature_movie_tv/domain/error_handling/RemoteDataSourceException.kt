package com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling

sealed class RemoteDataSourceException(val messageResource: Any?) : RuntimeException() {

    class Connection(messageResource: Int) : RemoteDataSourceException(messageResource)

    class UnExcepted(messageResource: Int) : RemoteDataSourceException(messageResource)

    class Timeout(messageResource: Int) : RemoteDataSourceException(messageResource)

    class Client(messageResource: Int) : RemoteDataSourceException(messageResource)

    class Server(messageResource: Int) : RemoteDataSourceException(messageResource)

}