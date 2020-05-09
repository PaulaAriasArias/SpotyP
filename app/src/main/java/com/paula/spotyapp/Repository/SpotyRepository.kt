package com.paula.spotyapp.Repository

import com.paula.spotyapp.AlbumObjectModel
import com.paula.spotyapp.models.AlbumModel
import com.paula.spotyapp.models.SongModel
import com.paula.spotyapp.models.SongObjectModel
import com.paula.spotyapp.service.ServiceFactory
import com.paula.spotyapp.service.SpotyServices

class SpotyRepository {
    private var spotyServices: SpotyServices

    init {
        val servicesFactory = ServiceFactory()
        spotyServices= servicesFactory.getInstanceSpotyService()

    }

    fun getAlbums(artist: Int) :List<AlbumModel> {
        try {
            val call : retrofit2.Call<List<AlbumObjectModel>> = spotyServices.getAlbums(artist)
            val response = call.execute()
            if (response.isSuccessful){
                return response.body()!![0].albums
            }else{
                throw Exception(response.errorBody().toString())
            }
        }catch (exception: Exception){
            throw exception
        }
    }

    fun getSongsByAlbum(albumId: Int): List<SongModel> {
        try {
            val call: retrofit2.Call<List<SongObjectModel>> = spotyServices.getSongsByAlbum(albumId)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!![0].songs
            } else {
                throw Exception(response.errorBody().toString())
            }

        } catch (exception: Exception) {
            throw exception
        }
    }

}