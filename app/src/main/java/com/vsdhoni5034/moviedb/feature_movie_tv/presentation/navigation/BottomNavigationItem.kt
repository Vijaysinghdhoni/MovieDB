package com.vsdhoni5034.moviedb.feature_movie_tv.presentation.navigation


import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unSelectedIcon: Painter
)
