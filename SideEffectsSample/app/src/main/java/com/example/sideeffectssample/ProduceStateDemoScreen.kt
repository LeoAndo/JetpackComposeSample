package com.example.sideeffectssample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProduceStateDemoScreen(modifier: Modifier) {
    // val uiState by loadNetworkImage(url = "", imageRepository = ImageRepository()) // 異常系
    val uiState by loadNetworkImage(url = "abc", imageRepository = ImageRepository()) // 正常系
    ProduceStateDemoScreenStateless(modifier, uiState)
}

@Composable
fun ProduceStateDemoScreenStateless(modifier: Modifier, uiState: UiResult) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is UiResult.Error -> Text(text = uiState.message)
            UiResult.Loading -> CircularProgressIndicator()
            is UiResult.Success -> Image(imageVector = uiState.image, contentDescription = "image")
        }
    }
}

@Composable
fun loadNetworkImage(
    url: String,
    imageRepository: ImageRepository
): State<UiResult> {
    /*
    Result.Loading を初期値として State<T> を作成します `url` または `imageRepository` のいずれかが変更された場合、
    実行中のプロデューサーはキャンセルされ、新しい入力で再起動されます。
     */
    return produceState<UiResult>(initialValue = UiResult.Loading, url, imageRepository) {
        // coroutineでsuspend関数を呼び出すことが可能
        // エラーまたは成功の結果で状態を更新します。これにより、この状態が読み取られる再構成がトリガーされます

        val image = imageRepository.load(url)
        value = if (image == null) {
            UiResult.Error("error発生！！")
        } else {
            UiResult.Success(image)
        }
    }
}
