package com.mmeruga.roomdatabase.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mmeruga.roomdatabase.database.User
import com.mmeruga.roomdatabase.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllUserData
    }

    fun getUserData(): LiveData<List<User>> {
        return readAllData
    }


    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}