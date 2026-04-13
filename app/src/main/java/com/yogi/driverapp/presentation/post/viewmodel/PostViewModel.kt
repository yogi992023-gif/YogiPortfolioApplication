package com.yogi.driverapp.presentation.post.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.driverapp.data.model.request.PostRequestDto
import com.yogi.driverapp.domain.usecase.GetCategoriesUseCase
import com.yogi.driverapp.domain.usecase.GetPhotosUseCase
import com.yogi.driverapp.domain.usecase.GetPostsUseCase
import com.yogi.driverapp.domain.usecase.GetProductsUseCase
import com.yogi.driverapp.presentation.post.effect.PostUiEffect
import com.yogi.driverapp.presentation.post.effect.PostUiEffect.*
import com.yogi.driverapp.presentation.post.event.PostUiEvent
import com.yogi.driverapp.presentation.post.state.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getPhotosUseCase : GetPhotosUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState
    private val _effect = Channel<PostUiEffect>()
    val effect = _effect.receiveAsFlow()
    private val pageSize = 10


    fun onEvent(event: PostUiEvent) {
        when (event) {
            is PostUiEvent.LoadPosts -> loadPosts()
            is PostUiEvent.OnItemClick -> {
                viewModelScope.launch {
                    _effect.send(NavigateToDetail)
                }
            }
            is PostUiEvent.OnSubmit -> {
                viewModelScope.launch {
                    _effect.send(ShowToast("Submitted: ${event.data}"))
                }
            }
            is PostUiEvent.OnBodyChange -> {
                _uiState.value = _uiState.value.copy(body = event.value)
            }
            is PostUiEvent.OnTitleChange -> {
                _uiState.value = _uiState.value.copy(title = event.value)
            }
            is PostUiEvent.Submit -> {
                createPost()
            }
            is PostUiEvent.LoadCategories -> {
                loadCategories()
            }
            is PostUiEvent.OnCategorySelect -> {
                Log.e("Categrory",event.category.name)
                _uiState.value = _uiState.value.copy(selectedCategory = event.category)
            }
            PostUiEvent.LoadPhotos -> {

            }

            PostUiEvent.LoadProductList -> {
               loadProducts()
            }
            PostUiEvent.LoadMore -> {
                if (!_uiState.value.isLoading && !_uiState.value.isEndReached) {
                    loadProducts()
                }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try{
                val result = async { getCategoriesUseCase() }
                val photoResult = async { getPhotosUseCase() }

                val category = result.await()
                val photolist = photoResult.await()

                Log.e("Categories", result.toString())
                _uiState.value = _uiState.value.copy(
                    categories = category,
                    listPhoto = photolist.take(10),
                    isLoading = false
                )
            }catch (e : Exception){
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    private fun createPost()  {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try{
                val request = PostRequestDto(title = _uiState.value.title, body = _uiState.value.body,userId = uiState.value.selectedCategory?.id ?: 1)
                val response = getPostsUseCase(request)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    successMessage = "Post Created ID: ${response.id}"
                )

                _effect.send(PostUiEffect.ShowToast("Post Created ID: ${response.id}"))
            }catch (e : Exception){
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
                _effect.send(PostUiEffect.ShowToast(e.message ?: "Unknown Error"))
            }

        }
    }


    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val postsDeferred = async { getPostsUseCase() }

                val posts = postsDeferred.await()

                _uiState.value = _uiState.value.copy(
                    list = posts,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
                _effect.send(PostUiEffect.ShowToast(e.message ?: "Unknown Error"))
            }
        }
    }

    private fun loadProducts() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true)

            try {

                val skip = _uiState.value.page * pageSize

                val response = getProductsUseCase(pageSize, skip)

                val newList = _uiState.value.productList + response.products

                _uiState.value = _uiState.value.copy(
                    productList = newList,
                    page = _uiState.value.page + 1,
                    isEndReached = newList.size >= response.total,
                    isLoading = false
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}
