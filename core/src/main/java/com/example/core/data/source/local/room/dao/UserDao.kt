package com.example.core.data.source.local.room.dao

import androidx.room.*
import com.example.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Query("DELETE FROM user")
    suspend fun deleteUser()

    @Transaction
    suspend fun insertAndDeleteUser(user: List<UserEntity>) {
        deleteUser()
        insertUser(user)
    }

    @Transaction
    @Query("SELECT * FROM user where login LIKE '%'|| :search || '%'")
    fun getSearchUser(search: String): Flow<List<UserEntity>>

}