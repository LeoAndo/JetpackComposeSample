package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
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
        MaterialTheme {
            val typography = MaterialTheme.typography
            val image: Painter = painterResource(id = R.drawable.header)
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                val imageModifier = Modifier
                    .height(180.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = imageModifier,
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "AAABBBCCCDDDEEE",
                    style = typography.h1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "DEF", style = typography.body1)
                Text(text = "GHI", style = typography.body2)
            }
        }
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        NewsStory()
    }
}