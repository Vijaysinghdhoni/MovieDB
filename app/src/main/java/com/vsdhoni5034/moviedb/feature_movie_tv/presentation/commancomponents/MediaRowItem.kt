package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents

import androidx.compose.foundation.clickable
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
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.toFullImageUrl

@Composable
fun MediaRowItem(
    modifier: Modifier = Modifier,
    mediaHome: MediaHome,
    onCLick: (Int) -> Unit
) {
//210 130

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
            .padding(8.dp)
            .size(width = 130.dp, height = 210.dp)
            .clickable {
                onCLick(mediaHome.id)
            }
    ) {

        Column {
            AsyncImage(
                model = mediaHome.posterPath.toFullImageUrl(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_placeholder_ic),
                error = painterResource(id = R.drawable.error_place_holder),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            )

            Text(
                text = mediaHome.titleName,
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
