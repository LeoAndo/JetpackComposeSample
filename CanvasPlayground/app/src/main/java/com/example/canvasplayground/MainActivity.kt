package com.example.canvasplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.canvasplayground.ui.theme.CanvasPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpleCanvasView2()
                }
            }
        }
    }
}

@Composable
fun SimpleCanvasView() {
    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
        drawRect(Color.Black)
        inset(150.0f) {
            drawLine(
                color = Color.White, start = Offset.Zero, end = Offset(size.width, size.height),
                strokeWidth = 10.0f
            )
        }
    })
}

@Composable
fun SimpleCanvasView2() {
    val px50toDp = 50f / LocalContext.current.resources.displayMetrics.density
    val image = ImageBitmap.imageResource(id = R.drawable.pokemon)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(px50toDp.dp)
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = px50toDp.dp),
            onDraw = {
                drawRect(Color.Cyan)
                drawImage(image = image)
            }
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            onDraw = {
                drawRect(Color.Yellow)
                drawImage(
                    image = image,
                    srcOffset = IntOffset(50, 50),
                    srcSize = IntSize(image.width - 50 - 50, (image.height - 50 - 50))
                )
                drawImage(image = image, topLeft = Offset(image.width.toFloat(), 0f))
            }
        )
    }
}

@Composable
fun SimpleCanvasView3() {
    val px50toDp = 50f / LocalContext.current.resources.displayMetrics.density
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(px50toDp.dp),
        onDraw = {
            drawRect(Color.Cyan)

            val path1 = Path()
            path1.addRect(
                Rect(0f, 100f, 200f, 400f)
            )
            path1.lineTo(400f, 50f)
            drawPath(
                path = path1,
                brush = Brush.linearGradient(
                    colors = arrayListOf(Color.Red, Color.Blue),
                    end = Offset(200f, 400f)
                ),
                style = Stroke(50f),
            )

            val path2 = Path()
            path2.addOval(Rect(0f, 500f, 200f, 900f))
            path2.lineTo(300f, 1000f)
            path2.lineTo(900f, 500f)
            drawPath(path = path2, color = Color.Black, alpha = 0.6f)
        }
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    CanvasPlaygroundTheme {
        SimpleCanvasView()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview2() {
    CanvasPlaygroundTheme {
        SimpleCanvasView2()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview3() {
    CanvasPlaygroundTheme {
        SimpleCanvasView3()
    }
}