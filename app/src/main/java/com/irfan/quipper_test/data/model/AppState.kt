package com.irfan.quipper_test.data.model

sealed class AppState<out T> {
    data object Loading : AppState<Nothing>()
    data class Success<out T>(val data: T) : AppState<T>()
    data class Error(val message: String) : AppState<Nothing>()
}