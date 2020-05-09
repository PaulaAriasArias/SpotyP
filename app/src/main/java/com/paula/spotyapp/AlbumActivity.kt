package com.paula.spotyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.paula.spotyapp.Listener.ListenerAlbum
import com.paula.spotyapp.Repository.SpotyRepository
import com.paula.spotyapp.Utils.ITEM_ALBUM
import com.paula.spotyapp.Utils.ValidateInternet
import kotlinx.android.synthetic.main.activity_album.*
import com.paula.spotyapp.models.AlbumModel

class AlbumActivity : AppCompatActivity(), ListenerAlbum {
    val validateInternet =ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        validateInternetToGetAlbums()
    }

    private fun validateInternetToGetAlbums(){
        if(validateInternet.isInternetAvailable(this))
        {
            createThreadToGetAlbums()
        }else
        {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(R.string.error_internet)
                .setPositiveButton("Reintentar"){_,_ ->
                    validateInternetToGetAlbums()
                }
                .setNegativeButton("Salir"){listener, _ ->
                    //listener.dismiss()
                    finish()
                }
                .create()
                .show()
        }
    }

    override fun onClickedAlbum(bundle: Bundle?, album: AlbumModel) {
        val intent = Intent(this,SongActivity::class.java)
        intent.putExtra(ITEM_ALBUM,album)
        startActivity(intent, bundle)

    }


    private fun createThreadToGetAlbums() {
        val thread = Thread(Runnable {
            getAlbumsFromRepository()
        })
        thread.start()
    }
    private fun getAlbumsFromRepository() {
        try {
            val repository= SpotyRepository()
            val result = repository.getAlbums(5)
            loadAdapter(result)
        }catch(e: Exception){
            runOnUiThread {
                Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadAdapter(result: List<AlbumModel>) {
        runOnUiThread{
            recyclerViewAlbum.layoutManager = GridLayoutManager(this,2)
            recyclerViewAlbum.adapter = AlbumAdapter(result,this)


        }
    }


}
