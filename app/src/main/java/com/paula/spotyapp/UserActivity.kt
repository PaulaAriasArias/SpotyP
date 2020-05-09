package com.paula.spotyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.paula.spotyapp.Repository.UserRepository
import com.paula.spotyapp.models.UserModel
import kotlinx.android.synthetic.main.activity_user.*
import java.lang.Exception

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        buttonA.setOnClickListener{
            val userModel= UserModel("Paula","lcz","paula@paula.com","1234")
            createThreadToCreateUser(userModel)
        }
    }

    private fun createThreadToCreateUser(userModel: UserModel) {
        val thread = Thread(Runnable {
            createUserFromRepository(userModel)
        })
        thread.start()
    }

    private fun createUserFromRepository(userModel: UserModel){
        try{
            val repository= UserRepository()
            val result= repository.createUser(userModel)
            showUSer(result)
        }catch (e: Exception)
        {
            runOnUiThread{
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showUSer(userModel: UserModel){
        runOnUiThread{
            Toast.makeText(this,"${userModel.name} ${userModel._id}",Toast.LENGTH_LONG).show()
        }
    }
}
