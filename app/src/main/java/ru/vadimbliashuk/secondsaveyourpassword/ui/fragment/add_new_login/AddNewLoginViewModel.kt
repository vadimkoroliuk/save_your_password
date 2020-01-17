package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.add_new_login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vadimbliashuk.secondsaveyourpassword.data.UserDatabase
import ru.vadimbliashuk.secondsaveyourpassword.data.UserRepository
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity

class AddNewLoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(website: String, login: String, password: String) = viewModelScope.launch {
        val user = UserEntity(website, login, password)
        repository.insert(user)
    }
}
