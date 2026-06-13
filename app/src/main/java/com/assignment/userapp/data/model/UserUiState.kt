package com.assignment.userapp.data.model

sealed class UserUiState {
    data object Loading: UserUiState()
    data class Success(val user:List<User>): UserUiState()
    data class Error(val message: String): UserUiState()
}