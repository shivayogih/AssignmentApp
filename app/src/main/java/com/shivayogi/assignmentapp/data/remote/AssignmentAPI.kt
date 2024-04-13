package com.shivayogi.assignmentapp.data.remote

import com.shivayogi.assignmentapp.data.responses.PostItem
import com.shivayogi.assignmentapp.data.responses.PostList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AssignmentAPI {

    @GET("posts")
    suspend fun getPostList(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<PostItem>

}