package com.paula.spotyapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.paula.spotyapp.Listener.ListenerSong
import com.paula.spotyapp.Repository.SpotyRepository
import com.paula.spotyapp.Utils.ITEM_ALBUM
import com.paula.spotyapp.models.AlbumModel
import com.paula.spotyapp.models.SongModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*

class SongActivity : AppCompatActivity(),ListenerSong {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        rvSongs.layoutManager= LinearLayoutManager(this)
        //val songs  =(0..20).map {
        //    SongModel("Cancion $it","1:10","")
        //}


        var album: AlbumModel?=null

        intent?.let{myIntent ->
            album = myIntent.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            Picasso.with(this).load(album!!.image).into(imgHeaderDetail)
            txtTitleDetail.text = album!!.name

        }

        createThreadToGetSongs(album!!.id)
    }

    private fun createThreadToGetSongs(idAlbum: Int)
    {
        val thread = Thread(Runnable {
            getSongsFromRepository(idAlbum)
        })
        thread.start()
    }

    private fun getSongsFromRepository(idAlbum: Int) {
        try {
            val repository= SpotyRepository()
            val result = repository.getSongsByAlbum(idAlbum)
            loadAdapter(result)
        }catch(e: Exception){
            runOnUiThread{
                Toast.makeText(this,e.message?:"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadAdapter(result: List<SongModel>) {
        runOnUiThread{
            val adapterSong = SongAdapter(result, this)
            rvSongs.adapter = adapterSong

        }
    }

    override fun onClickedSong(urlSong: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(urlSong))
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }
}
