package com.shivayogi.assignmentapp.ui.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shivayogi.assignmentapp.data.responses.PostItem
import com.shivayogi.assignmentapp.ui.components.StandardToolbar
import com.shivayogi.assignmentapp.ui.theme.SpaceLarge
import com.shivayogi.assignmentapp.ui.theme.SpaceMedium
import com.shivayogi.assignmentapp.util.toObject


@Composable
fun PostDetailsScreen(
    navController: NavController, args: String
) {
    var post: PostItem? = null
    args?.let {
        post = it.toObject<PostItem>()
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = "Post Details", fontWeight = FontWeight.Bold, color = Color.Blue
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,

            )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(MaterialTheme.colors.background)
                ) {
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    post?.id?.let {
                        Text(
                            text = "Post Id:$it",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h5,
                        )
                    }
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    post?.title?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h5,
                        )
                    }

                    Spacer(modifier = Modifier.height(SpaceMedium))
                    post?.body?.let {
                        Text(
                            text = it,
                            fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }

        }


    }

}