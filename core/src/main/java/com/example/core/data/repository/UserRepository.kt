package com.example.core.data.repository

import com.example.core.data.NetworkBoundResourceWithDeleteLocalData
import com.example.core.data.Resource
import com.example.core.data.source.local.room.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.UserResponse
import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserRepository
import com.example.core.helper.datamapper.DataMapperUser
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    override fun getUser(): Flow<Resource<List<User>>> =
        object :
            NetworkBoundResourceWithDeleteLocalData<List<User>, List<UserResponse>>() {

            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource.getUser().map {
                    DataMapperUser.mapEntitiestoDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getUser()

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val list = DataMapperUser.mapResponsetoEntities(data)
                localDataSource.insertUser(list)
            }

            override suspend fun emptyDataBase() {
                localDataSource.deleteUser()
            }

        }.asFlow()

    override fun getSearchUser(search: String): Flow<List<User>> {
        return localDataSource.getSearchUser(search).map {
            DataMapperUser.mapEntitiestoDomain(it)
        }
    }

}