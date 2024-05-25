package com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vsdhoni5034.moviedb.feature_movie_tv.data.data_source.MovieDbService
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PopularTvShowsPagingSource @Inject constructor(
    private val movieDbService: MovieDbService
) : PagingSource<Int, TvShowDto>() {
    override fun getRefreshKey(state: PagingState<Int, TvShowDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowDto> {
        return try {
            val page = params.key ?: 1
            val popularTvShows = movieDbService.getPopularTvShows(page)
            LoadResult.Page(
                data = popularTvShows.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (popularTvShows.results.isEmpty()) null else popularTvShows.page + 1
            )
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}