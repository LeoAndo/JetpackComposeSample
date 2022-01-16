package com.example.shrinematerialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.shrinematerialapp.ui.component.CartItem
import com.example.shrinematerialapp.ui.theme.ShrineMaterialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShrineMaterialAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    LazyColumn(content = {
        itemsIndexed(SampleItemsData) { key, item ->
            CartItem(item = item)
        }
    })
}

@Preview("MainScreen preview", showBackground = true)
@Composable
fun MainScreenPreview() {
    ShrineMaterialAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen()
        }
    }
}