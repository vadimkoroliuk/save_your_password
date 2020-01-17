package ru.vadimbliashuk.secondsaveyourpassword.data

import androidx.lifecycle.LiveData
import ru.vadimbliashuk.secondsaveyourpassword.models.UserEntity

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<UserEntity>> = userDao.getAll()

    suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    suspend fun update(user: UserEntity) {
        userDao.update(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.delete(user)
    }
}

//class UserViewModel(application: Application) : AndroidViewModel(application) {
//    private val repository: UserRepository
//    val allUsers: LiveData<List<UserEntity>>
//
//    init {
//        val userDao = UserDatabase.getInstance(application).userDao()
//        repository = UserRepository(userDao)
//        allUsers = repository.allUsers
//    }
//
//    fun insert(user: UserEntity) = viewModelScope.launch {
//        repository.insert(user)
//    }
//
//    fun update(user: UserEntity) = viewModelScope.launch {
//        repository.update(user)
//    }
//
//    fun delete(user: UserEntity) = viewModelScope.launch {
//        repository.delete(user)
//    }
//}