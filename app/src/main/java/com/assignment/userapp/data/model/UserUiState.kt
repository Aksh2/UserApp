package com.assignment.userapp.data.model

sealed class UserUiState<out T> {
    data object Loading: UserUiState<Nothing>()
    data class Success<T>(val user:List<User>): UserUiState<T>()
    data class Error(val message: String): UserUiState<Nothing>()
}