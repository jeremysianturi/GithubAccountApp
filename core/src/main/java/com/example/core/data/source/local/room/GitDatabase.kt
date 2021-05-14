package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.UserEntity
import com.example.core.data.source.local.room.dao.UserDao


@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class GitDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

}