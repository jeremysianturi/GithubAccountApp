package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.GitDatabase
import com.example.core.data.source.local.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GitDatabase = Room.databaseBuilder(
        context,
        GitDatabase::class.java, "GIT.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserDao(database: GitDatabase): UserDao = database.userDao()
    
}