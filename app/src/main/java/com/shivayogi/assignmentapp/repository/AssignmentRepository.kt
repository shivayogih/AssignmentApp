package com.shivayogi.assignmentapp.repository

import com.shivayogi.assignmentapp.data.remote.AssignmentAPI
import com.shivayogi.assignmentapp.data.responses.PostItem
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class AssignmentRepository @Inject constructor(
    private val api: AssignmentAPI
) {

    suspend fun getUserPostList(page: Int, limit: Int): Result<List<PostItem>> {
        val response = try {
            api.getPostList(page, limit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        return Result.success(response)
    }

}