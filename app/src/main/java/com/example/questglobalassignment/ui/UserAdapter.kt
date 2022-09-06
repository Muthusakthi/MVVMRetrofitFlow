package com.example.questglobalassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.questglobalassignment.data.Users
import com.example.questglobalassignment.databinding.AdapterUserBinding
import javax.inject.Inject


class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users = mutableListOf<Users>()
    private var clickInterface: ClickInterface<Users>? = null

    fun updateUsers(users: List<Users>) {
        this.users = users.toMutableList()
        notifyItemRangeInserted(0, users.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.view.tvName.text = user.first_name.plus(user.last_name)
        holder.view.tvEmail.text = user.email
        Glide
            .with(holder.view.imgUserImage.context)
            .load(user.avatar)
            .centerCrop()
            .into(holder.view.imgUserImage)
        holder.view.userCard.setOnClickListener {
            clickInterface?.onClick(user)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setItemClick(clickInterface: ClickInterface<Users>) {
        this.clickInterface = clickInterface
    }

    class UserViewHolder(val view: AdapterUserBinding) : RecyclerView.ViewHolder(view.root)
}

interface ClickInterface<T> {
    fun onClick(data: T)
}