package com.yogi.interviewproject.presentation.post.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yogi.interviewproject.R
import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.presentation.post.effect.PostUiEffect
import com.yogi.interviewproject.presentation.post.event.PostUiEvent
import com.yogi.interviewproject.presentation.post.viewmodel.PostViewModel

@Composable
fun ProductListScreen(navController: NavController,viewModel: PostViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                PostUiEffect.NavigateToDetail -> {
                    navController.navigate("LocationScreen")
                }
                PostUiEffect.ShowErrorDialog -> {}
                is PostUiEffect.ShowToast -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onEvent(PostUiEvent.LoadProductList)
    }

    // Pagination Trigger
    LaunchedEffect(listState) {

        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->

                val totalItems = listState.layoutInfo.totalItemsCount
                val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                if (lastVisibleItem >= totalItems - 2 && // preload
                    !state.isLoading) {
                    viewModel.onEvent(PostUiEvent.LoadMore)
                }
            }
    }

    LazyColumn(state = listState) {

        items(state.productList) { it ->
            ProductItem(it) {
                viewModel.onEvent(PostUiEvent.OnItemClick(it.id))
            }
        }

        item {
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
        }
    }

}

@Composable
fun ProductItem(product: ProductResponce.Product, onItemClick: (ProductResponce.Product) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{ onItemClick(product) }
            .padding(10.dp)
    ) {

        AsyncImage(
            model = product.thumbnail,
            placeholder = painterResource(id = R.drawable.kotlin),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

        Spacer(Modifier.width(10.dp))

        Text(product.title)
    }

    Divider()
}
