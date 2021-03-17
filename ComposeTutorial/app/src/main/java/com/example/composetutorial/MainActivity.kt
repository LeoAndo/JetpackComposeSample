package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }

    @Composable
    private fun NewsStory() {
        Column {
            Text(text = "ABC")
            Text(text = "DEF")
            Text(text = "GHI")
        }
    }
    
    @Preview
    @Composable
    fun DefaultPreview() {
        NewsStory()
    }
}