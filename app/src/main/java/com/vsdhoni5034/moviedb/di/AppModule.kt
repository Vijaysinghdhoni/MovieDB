package com.vsdhoni5034.moviedb.di

import com.vsdhoni5034.moviedb.BuildConfig
import com.vsdhoni5034.moviedb.feature_movie_tv.data.data_source.MovieDbService
import com.vsdhoni5034.moviedb.feature_movie_tv.data.datarepository.DataRepository
import com.vsdhoni5034.moviedb.feature_movie_tv.data.datarepository.DataRepositoryImpl
import com.vsdhoni5034.moviedb.feature_movie_tv.data.repository.RepositoryImpl
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.repository.Repository
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.AiringTodayTvShowsUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.DetailUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.MovieDetailUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.MoviesUseCases
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.NowPlayingMoviesUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.OnAirTvShowsUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.PopularMoviesUseCases
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.PopularTvShowsUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.TopRatedMoviesUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.TopRatedTvShowsUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.TvShowDetailUseCase
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.TvShowsUseCases
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.use_case.UpComingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val defaultTimOut = 20L
        //here we firstly get the current request from chain in form of original
        //then we get the url from that request and created a new url by adding apikey for every api call
        //then created a new request with that new url and added to chain
        return OkHttpClient()
            .newBuilder()
            .callTimeout(defaultTimOut, TimeUnit.SECONDS)
            .connectTimeout(defaultTimOut, TimeUnit.SECONDS)
            .readTimeout(defaultTimOut, TimeUnit.SECONDS)
            .writeTimeout(defaultTimOut, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val original = chain
                    .request()
                    .newBuilder()
                    .build()

                val originalHttpUrl: HttpUrl = original.url

                val url = originalHttpUrl
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                val requestBuilder: Request.Builder = original
                    .newBuilder()
                    .url(url)

                val newRequest: Request = requestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesMoviesDbService(retrofit: Retrofit): MovieDbService {
        return retrofit.create(MovieDbService::class.java)
    }

    @Provides
    @Singleton
    fun providesPagingRepository(movieDbService: MovieDbService): DataRepository {
        return DataRepositoryImpl(movieDbService)
    }

    @Provides
    @Singleton
    fun providesRepository(dataRepository: DataRepository): Repository {
        return RepositoryImpl(dataRepository)
    }

    @Provides
    @Singleton
    fun providesMoviesUseCases(repository: Repository): MoviesUseCases {
        return MoviesUseCases(
            topRatedMoviesUseCase = TopRatedMoviesUseCase(repository),
            nowPlayingMoviesUseCase = NowPlayingMoviesUseCase(repository),
            popularMoviesUseCases = PopularMoviesUseCases(repository),
            upComingMoviesUseCase = UpComingMoviesUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun providesTvShowsUseCases(repository: Repository): TvShowsUseCases {
        return TvShowsUseCases(
            airingTodayTvShowsUseCase = AiringTodayTvShowsUseCase(repository),
            onAirTvShowsUseCase = OnAirTvShowsUseCase(repository),
            popularTvShowsUseCase = PopularTvShowsUseCase(repository),
            topRatedTvShowsUseCase = TopRatedTvShowsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun providesDetailUseCase(repository: Repository): DetailUseCase {
        return DetailUseCase(
            movieDetailUseCase = MovieDetailUseCase(repository),
            tvShowDetailUseCase = TvShowDetailUseCase(repository)
        )
    }

}