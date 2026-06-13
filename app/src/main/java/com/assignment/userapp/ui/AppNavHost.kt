package com.assignment.userapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.assignment.userapp.ui.screens.UserListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.UserList.route
    ){
        composable(Screen.UserList.route){
            UserListScreen(onUserClick = {

            })
        }
        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(
                navArgument("userId"){
                    NavType.IntType
                }
            )
        ){

        }
    }
}