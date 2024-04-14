package com.shivayogi.assignmentapp.ui.posts


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shivayogi.assignmentapp.ui.components.StandardToolbar
import com.shivayogi.assignmentapp.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    navController: NavController, viewModel: PostListViewModel = hiltViewModel()
) {

    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController, title = {
                Text(
                    text = "All Posts",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }, modifier = Modifier.fillMaxWidth(), showBackArrow = false

        )

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(6.dp)
        ) {
            items(state.items.size) { i ->

                val item = state.items[i]
                if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.loadNextItems()
                }

                Card(
                    onClick = {
                        navController.navigate(Screen.PostDetailsScreen.createRoute(item.toJSON()))
                    }, shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item.id?.let {
                            Text(
                                text = "Post Id:$it",
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Black,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        item.title?.let {
                            Text(
                                text = it,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Black,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        item.body?.let {
                            Text(
                                text = it,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            item {
                if (state.isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if (state.error?.isNotEmpty() == true) {
                    RetrySection(error = state.error) {
                        viewModel.loadNextItems()
                    }
                }
            }
        }

    }
}


@Composable
fun RetrySection(
    error: String, onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() }, modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

