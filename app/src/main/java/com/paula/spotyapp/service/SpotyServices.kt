package com.paula.spotyapp.service

import com.paula.spotyapp.AlbumObjectModel
import com.paula.spotyapp.models.SongObjectModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotyServices {
    @GET("albums")
    fun getAlbums(@Query("artist") value: Int): Call<List<AlbumObjectModel>>

    @GET("songs")
    fun getSongsByAlbum(@Query("album") value: Int): Call<List<SongObjectModel>>
}




