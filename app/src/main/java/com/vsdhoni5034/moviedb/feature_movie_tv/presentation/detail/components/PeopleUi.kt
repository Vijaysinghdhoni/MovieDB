package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vsdhoni5034.moviedb.R
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.PersonUi
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.toFullImageUrl

@Composable
fun PeopleUi(
    modifier: Modifier = Modifier,
    personUi: PersonUi
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
            .padding(8.dp)
            .size(width = 130.dp, height = 210.dp)
    ) {
        Column {
            AsyncImage(
                model = personUi.profilePath.toFullImageUrl(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(id = R.drawable.loading_placeholder_ic),
                error = painterResource(id = R.drawable.error_place_holder),
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            )

            Text(
                text = personUi.name,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}