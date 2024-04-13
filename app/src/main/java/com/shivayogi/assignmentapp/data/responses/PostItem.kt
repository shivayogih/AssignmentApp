package com.shivayogi.assignmentapp.data.responses

import com.shivayogi.assignmentapp.util.JSONConvertable

data class PostItem(
    val body: String?=null,
    val id: Int?=null,
    val title: String?=null,
    val userId: Int?=null
): JSONConvertable