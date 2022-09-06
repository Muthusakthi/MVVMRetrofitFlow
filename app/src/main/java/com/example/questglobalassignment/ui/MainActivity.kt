package com.example.questglobalassignment.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.questglobalassignment.databinding.ActivityMainBinding
import com.example.questglobalassignment.data.Users
import com.example.questglobalassignment.data.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.adapter = userAdapter

        userAdapter.setItemClick(object : ClickInterface<Users> {
            override fun onClick(data: Users) {
                Toast.makeText(this@MainActivity, data.first_name.plus(data.last_name), Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.usersResponse.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {
                    binding.progressbar.isVisible = it.isLoading
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    binding.progressbar.isVisible = false
                }

                is  NetworkResult.Success -> {
                    userAdapter.updateUsers(it.data)
                    binding.progressbar.isVisible = false
                }
            }
        }

    }


}
