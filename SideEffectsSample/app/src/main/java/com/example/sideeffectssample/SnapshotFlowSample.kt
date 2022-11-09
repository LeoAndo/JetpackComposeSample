package com.example.sideeffectssample

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnapshotFlowSample(modifier: Modifier) {
    // LazyColumn の State を rememberLazyListState で保持しておく
    val listState = rememberLazyListState()

    // LazyColumn の State を Flow に変換する
    LaunchedEffect(listState) {
        snapshotFlow {
            Log.d("SnapshotFlowSample", "Enter")
            listState.firstVisibleItemIndex
        }.collect {
            Log.d("SnapshotFlowSample", "Collect FirstVisibleItemIndex $it")
        }
    }

    // listState オブジェクトを利用するように state に渡す
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        repeat(10) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp)
                ) {
                    Text(text = it.toString())
                }
            }
        }
    }
}