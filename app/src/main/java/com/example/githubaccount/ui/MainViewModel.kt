package com.example.githubaccount.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.UserUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val userUsecase: UserUsecase) :
    ViewModel()  {

    val searchQuery = MutableStateFlow("")

    private val curiculumFlow = searchQuery.flatMapLatest {
        userUsecase.getSearchUser(it)
    }

    val search = curiculumFlow.asLiveData()

    fun getUser() =
        userUsecase.getUser().asLiveData()

}