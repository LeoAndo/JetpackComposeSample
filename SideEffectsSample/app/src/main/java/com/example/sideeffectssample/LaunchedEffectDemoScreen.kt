package com.example.sideeffectssample

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun LaunchedEffectDemoScreen(modifier: Modifier) {
    var text by rememberSaveable { mutableStateOf("") }
    var count by rememberSaveable { mutableStateOf(0) }
    var text2 by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val dispatcher = Dispatchers.Default

    // key1に指定した値が変化したらblockの中の処理が実行される
    // Unitが指定され、値は変わらないので一度限りの実行になる。
    LaunchedEffect(key1 = Unit, block = {
        Log.d("TAG", "key1つ指定: IN thread: ${Thread.currentThread()}") // Thread[main,5,main]
        scope.launch(dispatcher) {
            Log.d(
                "TAG",
                "thread2: ${Thread.currentThread()}"
            ) // Thread[DefaultDispatcher-worker-2,5,main]
            delay(2000L)
            text = "text random result: ${Random.nextInt(3)}"
        }
    })

    // key1 or key2に指定した値が変化したらblockの中の処理が実行される
    LaunchedEffect(key1 = text, key2 = count, block = {
        Log.d("TAG", "key2つ指定: IN")
        text2 = "count: $count"
    })

    LaunchedEffectDemoScreenStateless(
        modifier = modifier.verticalScroll(rememberScrollState()),
        text = text,
        text2 = text2,
        onClickCountUpButton = {
            scope.launch(dispatcher) {
                delay(500L)
                count++
            }
        }
    )
}

@Composable
fun LaunchedEffectDemoScreenStateless(
    modifier: Modifier,
    text: String,
    text2: String,
    onClickCountUpButton: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = text)
        Text(text = text2)
        Button(onClick = { onClickCountUpButton() }) {
            Text(text = "Count Up")
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Prev_LaunchedEffectDemoScreen() {
    LaunchedEffectDemoScreenStateless(
        modifier = Modifier.fillMaxSize(),
        text = "sasasas",
        text2 = "AAssDSD",
        onClickCountUpButton = {},
    )
}