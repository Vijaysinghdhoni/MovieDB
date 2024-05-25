package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.vsdhoni5034.moviedb.R
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.DetailUi
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.toFullImageUrl

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    detailUi: DetailUi
) {

    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        ConstraintLayout {
            val (backdropRef, posterRef, detailRef) = createRefs()
            val backDropHeight = 250.dp

            AsyncImage(
                model = detailUi.backdropPath.toFullImageUrl(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading_placeholder_ic),
                error = painterResource(id = R.drawable.error_place_holder),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(backDropHeight)
                    .fillMaxWidth()
                    .constrainAs(backdropRef) {}
            )
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .height(backDropHeight.div(other = 1.5f))
                    .width(backDropHeight.div(other = 2f))
                    .padding(8.dp)
                    .constrainAs(posterRef) {
                        top.linkTo(backdropRef.bottom)
                        bottom.linkTo(backdropRef.bottom)
                        start.linkTo(backdropRef.start)
                    }
            ) {

                AsyncImage(
                    model = detailUi.posterPath.toFullImageUrl(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

            }

            FlowRow(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(vertical = 1.dp)
                    .constrainAs(detailRef) {
                        top.linkTo(backdropRef.bottom)
                        start.linkTo(posterRef.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ) {

                ElevatedAssistChip(onClick = { }, label = { Text(text = detailUi.genres.name) })
                ElevatedAssistChip(onClick = { }, label = { Text(detailUi.releaseDate) })

                if (!detailUi.runtTime.isNullOrEmpty()) {
                    ElevatedAssistChip(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.time_ic),
                                contentDescription = null
                            )
                        },
                        onClick = { },
                        label = { Text(text = detailUi.runtTime.toString()) })


                }
            }

        }

        Column(
            modifier = Modifier
                .padding(horizontal = 4.dp)
        ) {

            Text(
                text = detailUi.overView,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            PersonLazyRow(
                labelText = "Cast",
                list = detailUi.creditUi.cast
            )


            PersonLazyRow(
                labelText = "Crew",
                list = detailUi.creditUi.crew
            )

        }

    }
}