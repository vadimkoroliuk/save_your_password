package ru.vadimbliashuk.secondsaveyourpassword.ui.fragment.list_of_items

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.vadimbliashuk.secondsaveyourpassword.data.UserDatabase
import ru.vadimbliashuk.secondsaveyourpassword.data.UserRepository
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity

class ListOfItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers: LiveData<List<UserEntity>>

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    fun update(user: UserEntity) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: UserEntity) = viewModelScope.launch {
        repository.delete(user)
    }
}
