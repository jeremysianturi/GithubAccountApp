package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.data.repository.UserRepository
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository: UserRepository) :
    UserUsecase{

    override fun getUser(): Flow<Resource<List<User>>> =
        userRepository.getUser()

    override fun getSearchUser(search: String): Flow<List<User>> =
        userRepository.getSearchUser(search)

}