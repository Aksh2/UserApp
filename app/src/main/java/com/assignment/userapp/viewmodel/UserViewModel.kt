package com.assignment.userapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.userapp.data.ErrorMessages
import com.assignment.userapp.data.model.User
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
    private val _userListState = MutableStateFlow<UserUiState<List<User>>>(UserUiState.Loading)
    val userListState: StateFlow<UserUiState<List<User>>> = _userListState.asStateFlow()

    var selectedId: Int = -1
        private set
    private var cachedUsers: List<User> = emptyList()

    init {
        fetchUsers()
    }


    private fun fetchUsers() {
        viewModelScope.launch {
            _userListState.value = repository.fetchUsers().fold(
                onSuccess = { users ->
                    cachedUsers = users
                    UserUiState.Success(users)
                },
                onFailure = { UserUiState.Error(it.message ?: ErrorMessages.ERROR_UNKNOWN) }
            )
        }
    }

    fun retry() = fetchUsers()

    fun getUserById(id: Int): User? {
        selectedId = id
        return cachedUsers.find { it.id == id }
    }

}