package com.paula.spotyapp.models

import com.google.gson.annotations.SerializedName

class SongModel(
    @SerializedName("name")
    val title: String,
    @SerializedName("duration_ms")
    val time: String,
    @SerializedName("spotify_url")
    val url: String
)
