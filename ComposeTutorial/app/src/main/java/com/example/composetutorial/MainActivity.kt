package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsStory()
        }
    }

    @Composable
    private fun NewsStory() {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
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