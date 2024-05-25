package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.commancomponents

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.vsdhoni5034.moviedb.R
import com.vsdhoni5034.moviedb.feature_movie_tv.domain.model.MediaHome
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.Constants
import com.vsdhoni5034.moviedb.feature_movie_tv.presentation.util.toFullImageUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerShow(
    totalItemsToShow: Int = 10,
    labelText: String = "",
    list: LazyPagingItems<MediaHome>,
    autoScrollDuration: Long = Constants.CAROUSEL_AUTO_SCROLL_TIMER,
    onClick: (Int) -> Unit
) {
    val pageCount = list.itemCount.coerceAtMost(totalItemsToShow)
    val pagerState = rememberPagerState(pageCount = {
        pageCount
    })

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(
                            page = nextPage,
                            animationSpec = tween(
                                durationMillis = Constants.ANIM_TIME_LONG
                            )
                        )
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 32.dp
                ),
                pageSpacing = 16.dp
            ) { page ->
                val item = list[page]
                item?.let { movie ->
                    Card(
                        modifier = Modifier
                            .clickable {
                                onClick(movie.id)
                            }
                            .graphicsLayer {
                                val pageOffset = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue

                                val transformation = lerp(
                                    start = 0.8f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                                alpha = transformation
                                scaleY = transformation
                            },
                        shape = RoundedCornerShape(13.dp)
                    ) {
                        // Card content
                        Box(modifier = Modifier) {
                            AsyncImage(
                                model = movie.backdropPath.toFullImageUrl(),
                                contentDescription = null,
                                error = painterResource(id = R.drawable.ic_launcher_background),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .height(160.dp)
                                    .fillMaxWidth()
                            )

                            val gradient = remember {
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Black
                                    )
                                )
                            }

                            Text(
                                text = movie.titleName,
                                color = Color.White,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.BottomCenter)
                                    .background(gradient)
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 4.dp
                                    )
                            )
                        }
                    }
                }
            }
        }

        if (labelText.isNotEmpty()) {
            Text(
                text = labelText,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Preview() {
//    val ls = listOf(
//        Movie(
//            movieTitle = "The Black Days",
//            movieID = 1,
//            overview = "the black days movie with cooll staff",
//            posterPath = "bgfhjfbh",
//            backdropPath = ""
//        ),
//        Movie(
//            movieTitle = "The Black Days",
//            movieID = 1,
//            overview = "the black days movie with cooll staff",
//            posterPath = "bgfhjfbh",
//            backdropPath = ""
//        ),
//        Movie(
//            movieTitle = "The Black Days",
//            movieID = 1,
//            overview = "the black days movie with cooll staff",
//            posterPath = "bgfhjfbh",
//            backdropPath = ""
//        )
//    )
//
//    HorizontalPagerShow(list = ls, labelText = "UpComing Movies")
//}