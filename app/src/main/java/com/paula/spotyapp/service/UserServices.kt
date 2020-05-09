package com.paula.spotyapp.service

import com.paula.spotyapp.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserServices {
    @POST("users")
    fun createUser(@Body user: UserModel): Call<UserModel>

}