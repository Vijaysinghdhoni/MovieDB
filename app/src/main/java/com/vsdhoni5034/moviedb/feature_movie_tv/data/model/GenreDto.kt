package com.vsdhoni5034.moviedb.feature_movie_tv.data.model


import com.google.gson.annotations.SerializedName


data class GenreDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)