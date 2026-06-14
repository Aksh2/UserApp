package com.assignment.userapp.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.assignment.userapp.ui.screens.UserDetailsScreen
import com.assignment.userapp.ui.screens.UserListScreen
import com.assignment.userapp.viewmodel.UserViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.UserList.route) { backStackEntry ->
            val viewModel: UserViewModel = hiltViewModel(backStackEntry)
            UserListScreen(
                viewModel = viewModel,
                onUserClick = { user ->
                    user.id?.let {

                        navController.navigate(
                            Screen.UserDetail.createRoute(it)
                        )
                    }
                })
        }
        composable(
            route = Screen.UserDetail.route, arguments = listOf(
                navArgument("userId") {
                    type = NavType.IntType
                })
        ) { backStackEntry ->

            // get parent backstack entry
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.UserList.route)
            }

            // reuse the same viewmodel instance from the list
            val viewModel: UserViewModel = hiltViewModel(parentEntry)
            val userId = backStackEntry.arguments?.getInt("userId")



            // fetch the user from the cached list
            val user = userId?.let { viewModel.getUserById(it) }


            UserDetailsScreen(
                viewModel,
                onBackClick = { navController.popBackStack() })

        }
    }
}