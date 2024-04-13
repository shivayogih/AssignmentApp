package com.shivayogi.assignmentapp.data.responses

import com.shivayogi.assignmentapp.util.JSONConvertable


data class PostList(
    val count: Int = 100,
    val posts: List<PostItem>?= listOf()
): JSONConvertable
