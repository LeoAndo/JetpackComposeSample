package com.example.composenavigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.composenavigationsample.ui.theme.ComposeNavigationSampleTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MyApp() {
    ComposeNavigationSampleTheme {
        MainNavigation()
    }
}
