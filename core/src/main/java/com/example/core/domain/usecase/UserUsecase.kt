package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUsecase {

    fun getUser(): Flow<Resource<List<User>>>

    fun getSearchUser(search: String): Flow<List<User>>

}