package com.example.sideeffectssample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.sideeffectssample.ui.theme.SideEffectsSampleTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectsSampleTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(id = R.string.app_name))
                            },
                        )
                    },
                    content = { paddingValue ->
                        val modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValue)

//                        var showSplashScreen by rememberSaveable { mutableStateOf(true) }
//                        if (showSplashScreen) {
//                            SplashScreen(modifier, onTimeout = {
//                                showSplashScreen = false
//                                Log.d("TAG", "onTimeout: ")
//                            })
//                        } else {
//                            DisposableEffectDemoScreen(
//                                modifier = modifier,
//                                onStart = {
//                                    Log.d("TAG", "onStart イベント送信！")
//                                }, onStop = {
//                                    Log.d("TAG", "onStop イベント送信！")
//                                })
//                        }

                        // TodoList(modifier = modifier)
                        SnapshotFlowSample(modifier)

/*
                        DisposableEffectDemoScreen(
                            modifier = modifier,
                            onStart = {
                                Log.d("TAG", "onStart イベント送信！")
                            }, onStop = {
                                Log.d("TAG", "onStop イベント送信！")
                            })

 */
                        // ProduceStateDemoScreen(modifier)
                    })
            }
        }
    }
}