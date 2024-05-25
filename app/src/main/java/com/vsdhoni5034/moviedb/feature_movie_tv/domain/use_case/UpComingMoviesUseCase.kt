package com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.toMediaHome
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpComingMoviesUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<MediaHome>> {
        return repository.getUpComingMovies()
            .map {
                it.map { movieDto ->
                    movieDto.toMediaHome()
                }
            }
    }
}