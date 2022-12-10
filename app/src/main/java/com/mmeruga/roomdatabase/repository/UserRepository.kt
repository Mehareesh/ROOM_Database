package com.mmeruga.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.mmeruga.roomdatabase.database.User
import com.mmeruga.roomdatabase.database.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllUserData: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

//    fun deleteUser(id: Int) {
//        userDao.deleteUser(id)
//    }
}