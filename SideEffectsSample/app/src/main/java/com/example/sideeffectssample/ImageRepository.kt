package com.example.sideeffectssample

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.*

class ImageRepository(
    private val dispatchers: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun load(url: String): ImageVector? {
        var imageVector: ImageVector? = null
        return withContext(dispatchers) {
            delay(2000L) // ネットワーク通信をシミュレート
            if (url.isNotEmpty()) {
                imageVector = Icons.Default.Home
            }
            imageVector
        }
    }
}