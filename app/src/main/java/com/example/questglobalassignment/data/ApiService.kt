package com.example.questglobalassignment.data

import retrofit2.http.GET

interface ApiService {

    @GET("api/users?page=1")
    suspend fun getUsers() : UsersDataResponse
}