package com.vsdhoni5034.moviedb.feature_movie_tv.data.datarepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vsdhoni5034.moviedb.feature_movie_tv.data.data_source.MovieDbService
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.toDetailUi
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.AiringTodayTvShowsPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.NowPlayingMoviesPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.OnAirTvShowsPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.PopularMoviesPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.PopularTvShowsPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.TopRatedMoviesPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.TopRatedTvShowsPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource.UpComingMoviesPagingSource
import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Constants
import com.vsdhoni5034.moviedb.feature_movie_tv.data.util.Resource
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val movieDbService: MovieDbService
) : DataRepository {
    override fun getUpComingMovies(): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                UpComingMoviesPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getNowPLayingMovies(): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                NowPlayingMoviesPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getTopRatedMovies(): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                TopRatedMoviesPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getAiringTodayTvShows(): Flow<PagingData<TvShowDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                AiringTodayTvShowsPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getPopularTvShows(): Flow<PagingData<TvShowDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                PopularTvShowsPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getTopRatedTvShows(): Flow<PagingData<TvShowDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                TopRatedTvShowsPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }

    override fun getOnTheAirTvShows(): Flow<PagingData<TvShowDto>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.ITEMS_LOAD_PER_PAGE),
            pagingSourceFactory = {
                OnAirTvShowsPagingSource(
                    movieDbService = movieDbService
                )
            }
        ).flow
    }


    override fun getMovieDetail(id: Int): Flow<Resource<DetailUi>> = flow {
        val response = movieDbService.getMovieDetail(id)

        try {
            if (response.isSuccessful) {
                val movieDetail = response.body()
                movieDetail?.let {
                    emit(Resource.Success(it.toDetailUi()))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (ex: HttpException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        }
    }


    override fun getTvShowDetail(id: Int): Flow<Resource<DetailUi>> = flow {

        val response = movieDbService.getTvShowDetail(id)

        try {
            if (response.isSuccessful) {
                val tvShowDetail = response.body()
                tvShowDetail?.let {
                    emit(Resource.Success(it.toDetailUi()))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (ex: HttpException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        } catch (ex: IOException) {
            emit(Resource.Error(ex.localizedMessage ?: "Unknown error!"))
        }

    }
}