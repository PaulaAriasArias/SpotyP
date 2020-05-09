package com.paula.spotyapp.Repository

import com.paula.spotyapp.models.UserModel
import com.paula.spotyapp.service.ServiceFactory
import com.paula.spotyapp.service.UserServices

class UserRepository {
    private var userServices: UserServices

    init{
        val serviceFactory= ServiceFactory()
        userServices = serviceFactory.getInstanceUserService()

    }

    fun createUser(user: UserModel): UserModel {
        try {
            val call: retrofit2.Call<UserModel> = userServices.createUser(user)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

}