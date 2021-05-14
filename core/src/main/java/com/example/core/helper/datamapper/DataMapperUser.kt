package com.example.core.helper.datamapper

import com.example.core.data.source.local.entity.UserEntity
import com.example.core.data.source.remote.response.UserResponse
import com.example.core.domain.model.User

object DataMapperUser {

    fun mapResponsetoEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                login = it.login,
                id = it.id,
                avatarUrl = it.avatarUrl
            )
            userList.add(user)
        }

        return userList
    }

    fun mapEntitiestoDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                login = it.login,
                id = it.id,
                avatarUrl = it.avatarUrl
            )
        }

}