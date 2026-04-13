package com.yogi.driverapp.presentation.post.event
import com.yogi.driverapp.data.model.responce.Category
import com.yogi.driverapp.domain.model.Post

sealed class PostUiEvent {
    object LoadPosts : PostUiEvent()
    data class OnItemClick(val id: Int) : PostUiEvent()
    data class OnSubmit(val data: Post, val body: String) : PostUiEvent()
    data class OnTitleChange(val value: String) : PostUiEvent()
    data class OnBodyChange(val value: String) : PostUiEvent()
    object Submit : PostUiEvent()
    data class OnCategorySelect(val category: Category) : PostUiEvent()
    object LoadCategories : PostUiEvent()
    object LoadPhotos : PostUiEvent()
    object LoadProductList : PostUiEvent()

    object LoadMore : PostUiEvent()
}
