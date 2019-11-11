package ru.vadimbliashuk.secondsaveyourpassword.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import ru.vadimbliashuk.secondsaveyourpassword.R
import ru.vadimbliashuk.secondsaveyourpassword.data.UserViewModel
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity


class UserListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<UserEntity>() // Cached copy of users

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userItemWebsite: TextView = itemView.findViewById(R.id.tv_website_rv_item)
        val userItemLogin: TextView = itemView.findViewById(R.id.tv_login_rv_item)
        val userItemPassword: TextView = itemView.findViewById(R.id.tv_password_rv_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return UserViewHolder(itemView)
    }

    @SuppressLint("DefaultLocale")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.userItemWebsite.text = current.website.toUpperCase()
        holder.userItemLogin.text = current.login
        holder.userItemPassword.text = current.password

    }

    internal fun setUsers(users: List<UserEntity>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size

    fun getUserAtPosition(position: Int): UserEntity{
        return users[position]
    }
}

