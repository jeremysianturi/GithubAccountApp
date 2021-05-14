package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListUserResponse
import com.example.core.data.source.remote.response.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUser(): List<UserResponse>

}