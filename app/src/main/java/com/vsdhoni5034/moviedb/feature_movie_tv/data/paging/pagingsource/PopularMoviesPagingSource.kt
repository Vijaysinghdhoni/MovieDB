package com.vsdhoni5034.moviedb.feature_movie_tv.data.paging.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vsdhoni5034.moviedb.feature_movie_tv.data.data_source.MovieDbService
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDto
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling.RequestErrorHandler
import javax.inject.Inject

class PopularMoviesPagingSource @Inject constructor(
    private val movieDbService: MovieDbService
) : PagingSource<Int, MovieDto>() {

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            val page = params.key ?: 1
            val popularMovies = movieDbService.getPopularMovies(
                page = page
            )
            LoadResult.Page(
                data = popularMovies.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (popularMovies.results.isEmpty()) null else popularMovies.page + 1
            )
        }  catch (ex: Exception) {
            LoadResult.Error(throwable = RequestErrorHandler.getRequestedError(ex))
        }
    }


}