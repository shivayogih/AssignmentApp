package com.shivayogi.assignmentapp.util


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shivayogi.assignmentapp.data.responses.PostItem
import com.shivayogi.assignmentapp.ui.posts.PostDetailsScreen
import com.shivayogi.assignmentapp.ui.posts.PostListScreen


@Composable
fun Navigation() {

    val navController = rememberNavController()



    NavHost(
        navController = navController, startDestination = Screen.PostListScreen.route
    ) {

        composable(Screen.PostListScreen.route) {
            PostListScreen(navController = navController)
        }

        composable(
            route = Screen.PostDetailsScreen.route,
            arguments = listOf(navArgument("post") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("post")?.let {
                PostDetailsScreen(navController = navController, args = it)
            }
        }

    }
}