package com.assignment.userapp.network

import com.assignment.userapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}