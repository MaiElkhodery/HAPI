package com.example.hapi.data.model

sealed class State<out R: Any?> {
    data class Success<out R: Any>(val result: R?) : State<R>()
    data class Error(val error: ErrorBody) : State<Nothing>()
    data class Exception(val msg: String) : State<Nothing>()
    object Loading: State<Nothing>()
}