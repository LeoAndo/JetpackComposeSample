package com.example.sideeffectssample

import androidx.compose.ui.graphics.vector.ImageVector

sealed class UiResult {
    object Loading : UiResult()
    data class Error(val message: String) : UiResult()
    data class Success(val image: ImageVector) : UiResult()
}
