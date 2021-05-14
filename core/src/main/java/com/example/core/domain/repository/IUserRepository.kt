package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUser() : Flow<Resource<List<User>>>

    fun getSearchUser(search: String): Flow<List<User>>

}