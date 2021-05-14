package com.example.core.data.source.local.room

import com.example.core.data.source.local.entity.UserEntity
import com.example.core.data.source.local.room.dao.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val mUserDao: UserDao,
    ) {

    fun getUser(): Flow<List<UserEntity>> = mUserDao.getUser()
    suspend fun insertUser(user: List<UserEntity>) =
        mUserDao.insertAndDeleteUser(user)

    suspend fun deleteUser() = mUserDao.deleteUser()

    fun getSearchUser(search: String): Flow<List<UserEntity>> =
        mUserDao.getSearchUser(search)
    
}