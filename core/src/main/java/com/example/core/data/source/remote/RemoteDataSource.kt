package com.example.core.data.source.remote

import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService : ApiService
) {

    private val tag = RemoteDataSource::class.java.simpleName.toString()

    suspend fun getUser(): Flow<ApiResponse<List<UserResponse>>> {
        return flow {
            try {
                val response = apiService.getUser()
                val dataArray = response
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Timber.d("masuk ke remote data source yang ga empty ${e.message} dan ${e.cause}")
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
    
}