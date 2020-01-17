package ru.vadimbliashuk.secondsaveyourpassword.data

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