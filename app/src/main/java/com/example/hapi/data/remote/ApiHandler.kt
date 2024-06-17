package com.example.hapi.data.remote

import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

open class ApiHandler @Inject constructor(private val gson: Gson) {

    suspend fun <T : Any> makeRequest(
        execute: suspend () -> Response<T>,
        onSuccess: suspend (responseBody: T) -> Unit = {}
    ): Flow<State<T>> {
        return flow {
            emit(State.Loading)
            try {
                val response = execute()
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    onSuccess(responseBody)
                    emit(State.Success(responseBody))
                } else {
                    val error = parseError(response)
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                emit(handleException(e))
            }
        }
    }

    private fun parseError(response: Response<*>): SignupErrorInfo {
        return gson.fromJson(response.errorBody()?.string(), SignupErrorInfo::class.java)
    }

    private fun <T : Any> handleException(e: Exception): State<T> {
        return when (e) {
            is IOException -> State.Exception("Network error occurred. Please check your internet connection.")
            is HttpException -> State.Exception("Server error occurred. Please try again.")
            else -> State.Exception("Unknown error occurred.")
        }
    }
}

