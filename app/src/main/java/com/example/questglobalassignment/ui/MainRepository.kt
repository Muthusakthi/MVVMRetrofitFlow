package com.example.questglobalassignment.ui

import com.example.questglobalassignment.data.ApiService
import com.example.questglobalassignment.data.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllEmployeesData()  = flow {
        emit(NetworkResult.Loading(true))
        val response = apiService.getUsers()
       emit(NetworkResult.Success(response.data))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }
}