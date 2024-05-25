package com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case

import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.toDetailUi
import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Resource
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvShowDetailUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(showId: Int): Flow<Resource<DetailUi>> {
        return repository.getTvShowDetail(showId)
    }

}