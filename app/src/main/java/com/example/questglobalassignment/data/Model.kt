package com.example.questglobalassignment.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users (
    @PrimaryKey
    val id: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
    )

data class UsersDataResponse(val data: List<Users>, val errorMessage: String)

sealed class NetworkResult<T> {
    data class Loading<T>(val isLoading: Boolean) : NetworkResult<T>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val errorMessage: String) : NetworkResult<T>()
}

