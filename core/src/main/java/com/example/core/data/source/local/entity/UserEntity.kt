package com.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user")
data class UserEntity (

    @ColumnInfo(name = "login")
    val login: String,

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String

        )