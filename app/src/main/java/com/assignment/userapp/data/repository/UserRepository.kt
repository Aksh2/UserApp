package com.assignment.userapp.data.repository

import com.assignment.userapp.data.model.User

interface UserRepository {
    suspend fun fetchUsers(): Result<List<User>>
}