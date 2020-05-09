package com.paula.spotyapp.Listener

import android.os.Bundle
import com.paula.spotyapp.models.AlbumModel

interface ListenerAlbum {
    fun onClickedAlbum(
        bundle: Bundle?,
        album: AlbumModel
    )
}