package com.vsdhoni5034.moviedb.feature_movie_tv.domain.model

data class DetailUi(
    val backdropPath: String,
    val genres: GenreUi,
    val homePage: String,
    val id: Int,
    val originalTitle: String,
    val originalLanguage: String,
    val overView: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtTime: String?,
    val status: String,
    val tagLine: String,
    val title: String,
    val voteCount: Int,
    val voteAverage: Double,
    val creditUi: CreditUi
)
