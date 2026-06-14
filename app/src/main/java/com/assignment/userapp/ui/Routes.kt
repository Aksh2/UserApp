package com.assignment.userapp.ui


sealed class Screen(val route: String) {
    data object UserList : Screen("user_list")
    data object UserDetail : Screen("user_detail/{userId}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
}