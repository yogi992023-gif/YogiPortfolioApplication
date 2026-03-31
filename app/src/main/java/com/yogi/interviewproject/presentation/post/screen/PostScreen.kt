package com.yogi.interviewproject.presentation.post.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yogi.interviewproject.domain.model.Post
import com.yogi.interviewproject.presentation.post.effect.PostUiEffect
import com.yogi.interviewproject.presentation.post.event.PostUiEvent
import com.yogi.interviewproject.presentation.post.viewmodel.PostViewModel

@Composable
fun PostScreen(
    navController: NavController,
    viewModel: PostViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                PostUiEffect.NavigateToDetail -> {
                    navController.navigate("Post_Screen_Create")
                }
                PostUiEffect.ShowErrorDialog -> {}
                is PostUiEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(PostUiEvent.LoadPosts)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Posts", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        state.error?.let {
            Text("Error: $it", color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(state.list) { post ->
                PostItem(post) {
                    viewModel.onEvent(PostUiEvent.OnItemClick(it.id))
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post, onItemClick: (Post) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(post) }
            .padding(12.dp)
    ) {
        Text(post.title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(post.body)
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    PostScreen(navController = NavController(LocalContext.current))
    val navController = rememberNavController()
    // Note: hiltViewModel() won't work in Preview, typically you'd pass a mock or the state directly
    // For simplicity of this reorganization, I'm just fixing the call signature.
}
