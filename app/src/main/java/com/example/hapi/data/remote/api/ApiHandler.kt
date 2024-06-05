package com.example.hapi.data.remote.api

import com.example.hapi.domain.model.SignupErrorInfo
import com.example.hapi.domain.model.State
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

open class ApiHandler {
    suspend fun <T : Any> makeRequest(
        execute: suspend () -> Response<T>,
        onSuccess: suspend (responseBody: T) -> Unit = {}
    ): Flow<State<T>> {
        return flow {
            try {
                emit(State.Loading)
                val response = execute()
                if (response.isSuccessful) {
                    onSuccess(response.body()!!)
                    emit(State.Success(response.body()!!))
                } else {
                    val error = Gson().fromJson(
                        response.errorBody()?.string(),
                        SignupErrorInfo::class.java
                    )
                    emit(State.Error(error))
                }
            } catch (e: Exception) {
                emit(handleException(e))
            }
        }
    }


    fun <T : Any> handleException(e: Exception): State<T> {
        return when (e) {
            is IOException ->
                State.Exception("Network error occurred. Please check your internet connection.")

            is HttpException ->
                State.Exception("Server error occurred. Please try again.")

            else ->
                State.Exception("Unknown error occurred.")
        }
    }

}

