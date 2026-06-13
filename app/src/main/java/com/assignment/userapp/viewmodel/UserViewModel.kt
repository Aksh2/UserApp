package com.assignment.userapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.userapp.data.model.ErrorMessages
import com.assignment.userapp.data.model.UserUiState
import com.assignment.userapp.data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(private val repository: UserRepositoryImpl) : ViewModel() {
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    init {
        fetchUsers()
    }


    private fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = repository.fetchUsers().fold(
                onSuccess = { UserUiState.Success(it) },
                onFailure = { UserUiState.Error(it.message ?: ErrorMessages.ERROR_UNKNOWN) }
            )
        }
    }

}