package com.vsdhoni5034.moviedb.feature_movie_tv.data.data_source

import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MovieDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.MoviesDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowDetailDto
import com.vsdhoni5034.moviedb.feature_movie_tv.data.model.TvShowsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbService {

    //* movies api *//

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): MoviesDto

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") page: Int
    ): MoviesDto

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MoviesDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MoviesDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "videos,credits"
    ): Response<MovieDetailDto>

    //* tvshows api *//

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int
    ): TvShowsDto

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int
    ): TvShowsDto

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): TvShowsDto

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int
    ): TvShowsDto

    @GET("tv/{series_id}")
    suspend fun getTvShowDetail(
        @Path("series_id") seriesId: Int,
        @Query("append_to_response") appendToResponse: String = "videos,credits"
    ): Response<TvShowDetailDto>

    //setting screen
    //while naviagting from movie to tv screen problme in background
    //detail screen with sharedtransition
    //inCache paging using remote mediator
    //type safe naviagtion with new naviagtion update
}