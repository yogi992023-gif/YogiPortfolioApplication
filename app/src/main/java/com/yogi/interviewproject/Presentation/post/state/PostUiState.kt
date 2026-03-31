package com.yogi.interviewproject.presentation.post.state

import com.yogi.interviewproject.data.model.responce.Category
import com.yogi.interviewproject.data.model.responce.Photo
import com.yogi.interviewproject.data.model.responce.ProductResponce
import com.yogi.interviewproject.domain.model.Post

data class PostUiState(
    val isLoading: Boolean = false,
    val list: List<Post> = emptyList(),
    val title: String = "",
    val body: String = "",
    val successMessage: String? = null,
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val listPhoto: List<Photo> = emptyList(),
    val productList: List<ProductResponce.Product> = emptyList(),
    val page: Int = 0,
    val isEndReached: Boolean = false,
    val error: String? = null
)
