package com.yogi.interviewproject.Presentation.post.screen

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yogi.interviewproject.R
import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.data.model.responce.Photo
import com.yogi.interviewproject.presentation.post.effect.PostUiEffect
import com.yogi.interviewproject.presentation.post.event.PostUiEvent
import com.yogi.interviewproject.presentation.post.viewmodel.PostViewModel

@Composable
fun CreatePostScreen(navController: NavController,
    viewModel: PostViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Side Effects
    LaunchedEffect(Unit) {

        viewModel.effect.collect {

            when (it) {
                is PostUiEffect.ShowToast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    navController.navigate("ProductListScreen")
                }
                PostUiEffect.NavigateToDetail -> {

                }
                PostUiEffect.ShowErrorDialog -> {

                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(PostUiEvent.LoadCategories)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Create Post",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.title,
            onValueChange = {
                viewModel.onEvent(PostUiEvent.OnTitleChange(it))
            },
            label = { Text("Title") },
            placeholder = { Text("Enter post title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.body,
            onValueChange = {
                viewModel.onEvent(PostUiEvent.OnBodyChange(it))
            },
            label = { Text("Body") },
            placeholder = { Text("Enter post content") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 5
        )

        Spacer(Modifier.height(10.dp))
        Log.e("Categories screen++++", state.categories.toString() + "")
        CategoryDropdown(
            list = state.categories,
            selected = state.selectedCategory,
            onSelect = {
                viewModel.onEvent(PostUiEvent.OnCategorySelect(it))
            }
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.onEvent(PostUiEvent.Submit)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Submit Post")
        }

        Spacer(Modifier.height(20.dp))

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        state.successMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        state.error?.let {
            Text(
                text = "Error: $it",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(Modifier.height(10.dp))

        LazyRow {
            items(state.listPhoto) {
                photoItem(it)
            }
        }
    }
}

@Composable
fun photoItem(photo: Photo) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        AsyncImage(
            model = photo.thumbnailUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.kotlin),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .width(200.dp)
        )

        Spacer(Modifier.height(7.dp))

        Text(photo.title)

    }

    Divider()

}

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
fun CategoryDropdown(
    list: List<Category>,
    selected: Category?,
    onSelect: (Category) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selected?.name ?: "Select Category",
            onValueChange = {},
            readOnly = true,
            label = { Text("Category") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            modifier = Modifier
                .menuAnchor() // Critical: This modifier enables the dropdown logic on the text field
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            if (list.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("No categories found") },
                    onClick = { expanded = false },
                    enabled = false
                )
            } else {
                list.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = category.name) },
                        onClick = {
                            onSelect(category)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreatePostScreenPreview() {
    CreatePostScreen(navController = NavController(LocalContext.current))
}