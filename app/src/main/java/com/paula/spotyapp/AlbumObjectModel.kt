package com.paula.spotyapp

import com.google.gson.annotations.SerializedName
import com.paula.spotyapp.models.AlbumModel

class AlbumObjectModel (
    @SerializedName("artist")
    val artist: Int,
    val albums:List<AlbumModel>
)