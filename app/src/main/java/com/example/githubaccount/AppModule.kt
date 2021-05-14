package com.example.githubaccount

import com.example.core.domain.usecase.UserInteractor
import com.example.core.domain.usecase.UserUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUsecase

}