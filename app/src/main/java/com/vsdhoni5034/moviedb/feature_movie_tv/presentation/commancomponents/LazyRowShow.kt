package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome

@Composable
fun LazyRowShow(
    modifier: Modifier = Modifier,
    labelText: String,
    list: LazyPagingItems<MediaHome>,
    onClick: (Int) -> Unit
) {

    Column {

        if (list.itemCount > 0) {
            Text(
                text = labelText,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    5.dp
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
        LazyRow(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            state = rememberLazyListState()
        ) {

            items(list.itemCount) { index ->
                list[index]?.let { item ->
                    MediaRowItem(mediaHome = item,
                        onCLick = {
                            onClick(it)
                        }
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
            }

        }

    }
}
