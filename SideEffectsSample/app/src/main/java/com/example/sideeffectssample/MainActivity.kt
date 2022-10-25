package com.example.sideeffectssample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.sideeffectssample.ui.theme.SideEffectsSampleTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectsSampleTheme {
                Scaffold(content = { paddingValue ->
                    val modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                    LaunchedEffectDemoScreen(modifier)
                })
            }
        }
    }
}