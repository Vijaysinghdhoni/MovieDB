package com.vsdhoni5034.moviedb.feature_movie_tv.data.repository

import androidx.paging.PagingData
import com.vsdhoni5034.moviedb.feature_movie_tv.data.datarepository.DataRepository
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Resource
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataRepository: DataRepository,
) : Repository {
    override fun getUpComingMovies(): Flow<PagingData<MovieDto>> {
        return dataRepository.getUpComingMovies()
    }

    override fun getNowPLayingMovies(): Flow<PagingData<MovieDto>> {
        return dataRepository.getNowPLayingMovies()
    }

    override fun getPopularMovies(): Flow<PagingData<MovieDto>> {
        return dataRepository.getPopularMovies()
    }

    override fun getTopRatedMovies(): Flow<PagingData<MovieDto>> {
        return dataRepository.getTopRatedMovies()
    }

    override fun getAiringTodayTvShows(): Flow<PagingData<TvShowDto>> {
        return dataRepository.getAiringTodayTvShows()
    }

    override fun getPopularTvShows(): Flow<PagingData<TvShowDto>> {
        return dataRepository.getPopularTvShows()
    }

    override fun getTopRatedTvShows(): Flow<PagingData<TvShowDto>> {
        return dataRepository.getTopRatedTvShows()
    }

    override fun getOnTheAirTvShows(): Flow<PagingData<TvShowDto>> {
        return dataRepository.getOnTheAirTvShows()
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<DetailUi>> {
        return dataRepository.getMovieDetail(id = movieId)
    }

    override fun getTvShowDetail(tvShowId: Int): Flow<Resource<DetailUi>> {
        return dataRepository.getTvShowDetail(id = tvShowId)
    }

}