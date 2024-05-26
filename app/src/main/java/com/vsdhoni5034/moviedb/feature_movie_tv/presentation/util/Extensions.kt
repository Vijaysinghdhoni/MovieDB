package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util

import android.content.Context
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.vsdhoni5034.moviedb.BuildConfig
import com.vsdhoni5034.moviedb.R
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.error_handling.RemoteDataSourceException
import okhttp3.ResponseBody
import java.util.Locale


fun String.toFullImageUrl(): String {
    return BuildConfig.IMAGE_BASE_URL + this
}

fun Iterable<LazyPagingItems<*>>.isAnyRefreshing(): Boolean {
    return any {
        it.loadState.refresh is LoadState.Loading
    }
}

fun Iterable<LazyPagingItems<*>>.hasItems(): Boolean {
    return any {
        it.itemCount > 0
    }
}

fun Iterable<LazyPagingItems<*>>.isAnyError(): Pair<Boolean, LoadState.Error?> {
    return if (any { it.loadState.refresh is LoadState.Error }) {
        val errorList: LazyPagingItems<*>? = this.find { it.loadState.refresh is LoadState.Error }
        true to errorList?.loadState?.refresh as LoadState.Error
    } else {
        false to null
    }
}

fun Int.minuteToRelativeTime(): String {
    val hours: Int = this / 60
    val minutes: Int = this % 60
    return String.format(
        locale = Locale.ENGLISH,
        format = "%dh:%02dm",
        hours,
        minutes
    )
}

fun RemoteDataSourceException.getError(context: Context): String {
    return when (messageResource) {
        is Int -> context.getString(messageResource)
        is ResponseBody? -> messageResource!!.string()
        is String -> messageResource
        else -> context.getString(R.string.error_unexpected_message)
    }
}