package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.vsdhoni5034.moviedb.BuildConfig
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