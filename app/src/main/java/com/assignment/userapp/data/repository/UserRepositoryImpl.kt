package com.assignment.userapp.data.repository

import com.assignment.userapp.data.model.User
import com.assignment.userapp.network.UserService
import com.assignment.userapp.network.validateApiCall
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userService: UserService) : UserRepository {
    override suspend fun fetchUsers(): Result<List<User>> {
        return validateApiCall { userService.getUsers() }
    }
}