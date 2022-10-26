package com.example.sideeffectssample

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

/*
2022-10-26 12:44:23.264  8680-8680  SplashScreen            com.example.sideeffectssample        D   IN onTimeout hashCode: 91509586
2022-10-26 12:44:23.442  8680-8680  SplashScreen            com.example.sideeffectssample        D  currentOnTimeout: IN
2022-10-26 12:44:28.447  8680-8680  SplashScreen            com.example.sideeffectssample        D   onTimeout hashCode: 91509586
2022-10-26 12:44:28.447  8680-8680  SplashScreen            com.example.sideeffectssample        D   currentOnTimeout hashCode: 91509586
 */
@Composable
fun SplashScreen(modifier: Modifier, onTimeout: () -> Unit) {
    Log.d("SplashScreen", " IN onTimeout hashCode: " + onTimeout.hashCode())
    // これは、SplashScreenが再構成された最新のonTimeout関数を常に参照します。
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    val splashWaitTimeMillis = 5000L
    // SplashScreenが再構成された場合、LaunchedEffectが再び開始されることはありません。
    LaunchedEffect(Unit) {
        Log.d("SplashScreen", "currentOnTimeout: IN")
        delay(splashWaitTimeMillis)
        Log.d("SplashScreen", " onTimeout hashCode: " + onTimeout.hashCode())
        Log.d("SplashScreen", " currentOnTimeout hashCode: " + currentOnTimeout.hashCode())
        currentOnTimeout()
    }
    SplashScreenStateless(modifier = modifier)
}

@Composable
fun SplashScreenStateless(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "splash image"
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Prev_SplashScreen() {
    SplashScreenStateless(modifier = Modifier.fillMaxSize())
}