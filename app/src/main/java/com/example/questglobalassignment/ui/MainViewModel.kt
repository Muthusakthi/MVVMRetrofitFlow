package com.example.questglobalassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questglobalassignment.data.Users
import com.example.questglobalassignment.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private var _usersResponse = MutableLiveData<NetworkResult<List<Users>>>()
    val usersResponse: LiveData<NetworkResult<List<Users>>> = _usersResponse

    init {
        fetchAllData()
    }

    private fun fetchAllData() {
        viewModelScope.launch {
            mainRepository.getAllEmployeesData().collect {
                _usersResponse.postValue(it)
            }
        }
    }
}

