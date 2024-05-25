package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail.components

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
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.PersonUi

@Composable
fun PersonLazyRow(
    modifier: Modifier = Modifier,
    labelText:String,
    list: List<PersonUi>
) {

    Column {

        if (list.isNotEmpty()) {
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

            items(list.size) { index ->
                PeopleUi(personUi = list[index])
                Spacer(modifier = Modifier.width(4.dp))
            }

        }

    }

}