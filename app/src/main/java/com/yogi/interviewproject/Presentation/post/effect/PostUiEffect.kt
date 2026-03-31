package com.yogi.interviewproject.presentation.post.effect

sealed class PostUiEffect {
    data class ShowToast(val message: String) : PostUiEffect()
    object NavigateToDetail : PostUiEffect()
    object ShowErrorDialog : PostUiEffect()

}
