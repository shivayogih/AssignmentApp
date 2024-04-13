package com.shivayogi.assignmentapp.util


sealed class Screen(val route:String) {
    object PostListScreen : Screen("post_list")
    object PostDetailsScreen : Screen("post_details/{post}") {
        fun createRoute(post: String): String {
            return "post_details/$post"
        }
    }
}

