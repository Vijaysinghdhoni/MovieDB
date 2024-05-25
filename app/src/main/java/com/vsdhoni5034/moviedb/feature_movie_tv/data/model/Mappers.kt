package com.vsdhoni5034.moviedb.feature_movie_tv.data.model

import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Constants
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.CreditUi
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.GenreUi
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.PersonUi
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.minuteToRelativeTime


fun MovieDto.toMediaHome(): MediaHome {
    return MediaHome(
        id = id,
        titleName = title,
        posterPath = posterPath ?: Constants.EMPTY,
        backdropPath = backdropPath ?: Constants.EMPTY,
        overview = overview ?: Constants.EMPTY
    )
}

fun TvShowDto.toMediaHome(): MediaHome {
    return MediaHome(
        id = id,
        posterPath = posterPath ?: Constants.EMPTY,
        overview = overview ?: Constants.EMPTY,
        titleName = name,
        backdropPath = backdropPath ?: Constants.EMPTY
    )
}

fun MovieDetailDto.toDetailUi(): DetailUi {
    return DetailUi(
        backdropPath = backdropPath ?: Constants.EMPTY,
        genres = genres.toGenreUi(),
        homePage = homepage,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overView = overview ?: Constants.EMPTY,
        popularity = popularity,
        posterPath = posterPath ?: Constants.EMPTY,
        releaseDate = releaseDate,
        status = status,
        tagLine = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtTime = runtime?.minuteToRelativeTime(),
        creditUi = credits.toCreditUi()
    )
}

fun TvShowDetailDto.toDetailUi(): DetailUi {
    return DetailUi(
        backdropPath = backdropPath ?: Constants.EMPTY,
        genres = genres.toGenreUi(),
        homePage = homepage,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalName,
        overView = overview ?: Constants.EMPTY,
        popularity = popularity,
        posterPath = posterPath ?: Constants.EMPTY,
        releaseDate = firstAirDate,
        status = status,
        tagLine = tagline,
        title = name,
        voteAverage = voteAverage,
        voteCount = voteCount,
        runtTime = episodeRunTime.firstOrNull()?.minuteToRelativeTime(),
        creditUi = credits.toCreditUi()
    )
}

fun List<GenreDto>.toGenreUi(): GenreUi {
    return map {
        GenreUi(
            id = it.id,
            name = it.name
        )
    }.firstOrNull() ?: GenreUi(
        id = 0,
        name = Constants.EMPTY
    )
}

fun CreditsDto.toCreditUi(): CreditUi {
    val totalItemCount = 10
    return CreditUi(
        cast = cast.take(totalItemCount).mapToCast(),
        crew = crew.take(totalItemCount).mapToCrew()
    )
}

fun List<Crew>.mapToCrew(): List<PersonUi> {
    return map {
        PersonUi(
            id = it.id,
            creditId = it.creditId,
            name = it.name,
            role = it.job,
            profilePath = it.profilePath ?: Constants.EMPTY
        )
    }
}


fun List<Cast>.mapToCast(): List<PersonUi> {
    return map {
        PersonUi(
            id = it.id,
            creditId = it.creditId,
            name = it.name,
            role = it.character,
            profilePath = it.profilePath ?: Constants.EMPTY
        )
    }
}