package com.vsdhoni5034.moviedb.feature_movie_tv.data.datarepository

import androidx.paging.PagingData
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Resource
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun getUpComingMovies(): Flow<PagingData<MovieDto>>

    fun getNowPLayingMovies(): Flow<PagingData<MovieDto>>

    fun getPopularMovies(): Flow<PagingData<MovieDto>>

    fun getTopRatedMovies(): Flow<PagingData<MovieDto>>

    fun getAiringTodayTvShows(): Flow<PagingData<TvShowDto>>

    fun getPopularTvShows(): Flow<PagingData<TvShowDto>>

    fun getTopRatedTvShows(): Flow<PagingData<TvShowDto>>

    fun getOnTheAirTvShows(): Flow<PagingData<TvShowDto>>

    fun getMovieDetail(id: Int): Flow<Resource<DetailUi>>

    fun getTvShowDetail(id: Int): Flow<Resource<DetailUi>>
}