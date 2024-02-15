package com.example.hapi.util

import com.example.hapi.data.model.ErrorBody
import com.example.hapi.data.model.State
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

fun parseErrorBody(errorBody: String?, error: ErrorBody): Any? {
    return when (error){
        is ErrorBody.LandownerSignupError->{
            Gson().fromJson(errorBody, ErrorBody.LandownerSignupError::class.java)
        }
    }
}

fun handleException(e: Exception): State<Any> {
    return when (e) {
        is IOException ->
            State.Exception("Network error occurred during signup. Please check your internet connection.")

        is HttpException ->
            State.Exception("Server error occurred during signup. Please try again.")

        else ->
            State.Exception("Unknown error occurred during signup.")
    }
}