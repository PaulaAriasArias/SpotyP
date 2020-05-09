package com.paula.spotyapp.Listener

import android.os.Bundle
import com.paula.spotyapp.models.SongModel

interface ListenerSong {
    fun onClickedSong(
        urlSong: String

    )
}