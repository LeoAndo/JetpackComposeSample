package com.example.sideeffectssample

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
/*
初期起動時:
D  lifecycleOwner: 235078382
D  currentOnStart: 158823964
D  currentOnStop: 144728058
D  DisposableEffect IN, DisposableEffectScope: 120195569
D  onStart イベント送信！

ボタンクリック時：
D  lifecycleOwner: 235078382
D  currentOnStart: 158823964
D  currentOnStop: 144728058

画面回転時：
D  onStop イベント送信！
D  DisposableEffect onDispose, DisposableEffectScope: 120195569
D  lifecycleOwner: 69177602
D  currentOnStart: 158823964
D  currentOnStop: 144728058
D  DisposableEffect IN, DisposableEffectScope: 120195569
D  onStart イベント送信！
 */
@Composable
fun DisposableEffectDemoScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit,
    modifier: Modifier
) {
    // 新しいラムダが提供されたときに現在のラムダを安全に更新する
    val currentOnStart by rememberUpdatedState(newValue = onStart)
    val currentOnStop by rememberUpdatedState(newValue = onStop)
    var count by rememberSaveable { mutableStateOf(0) }

    Log.d("TAG", "lifecycleOwner: ${lifecycleOwner.hashCode()}")
    Log.d("TAG", "currentOnStart: ${currentOnStart.hashCode()}")
    Log.d("TAG", "currentOnStop: ${currentOnStop.hashCode()}")

    // 「lifecycleOwner」が変更された場合は、effectを破棄してリセットします
    DisposableEffect(key1 = lifecycleOwner, effect = {
        Log.d("TAG", "DisposableEffect IN, DisposableEffectScope: ${this.hashCode()}")
        // rememberされたコールバックをトリガーするオブザーバーを作成する
        val observer = LifecycleEventObserver { _, event ->
            if (Lifecycle.Event.ON_START == event) currentOnStart()
            if (Lifecycle.Event.ON_STOP == event) currentOnStop()
        }
        // オブザーバーをLifecycleに追加する
        lifecycleOwner.lifecycle.addObserver(observer)
        // effectがコンポジションを離れたら、オブザーバーを削除します
        onDispose {
            Log.d("TAG", "DisposableEffect onDispose, DisposableEffectScope: ${this.hashCode()}")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })
    /** screen content. */
    DisposableEffectDemoScreenStateless(
        modifier = modifier,
        onCountUp = { count++ },
        count = count,
    )
}

@Composable
fun DisposableEffectDemoScreenStateless(modifier: Modifier, onCountUp: () -> Unit, count: Int) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { onCountUp() }) {
            Text(text = count.toString())
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DisposableEffectDemoScreen_Prev() {
    DisposableEffectDemoScreenStateless(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onCountUp = { },
        count = 10,
    )
}