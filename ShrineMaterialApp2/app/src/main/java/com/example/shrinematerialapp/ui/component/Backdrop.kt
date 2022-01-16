package com.example.shrinematerialapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shrinematerialapp.R
import com.example.shrinematerialapp.ui.theme.ShrineMaterialAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Backdrop() {
    BackdropScaffold(
        appBar = {
            TopAppBar(title = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu_cut_24px),
                        contentDescription = "Menu Icon"
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Shrine".uppercase(), style = MaterialTheme.typography.subtitle1)
                }
            }, actions = {
                // Row Scopeで横に並べる.
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }, elevation = 0.dp)
        },
        frontLayerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text("This is the content for category: xxx")
            }
        },
        backLayerContent = {
            Column(modifier = Modifier.padding(32.dp)) {
                Text(text = "This is backLayerContent")
            }
        },
        frontLayerShape = MaterialTheme.shapes.large,
        frontLayerElevation = 16.dp
    )
}

@Preview(showBackground = true)
@Composable
fun BackDropPreview() {
    ShrineMaterialAppTheme {
        Backdrop()
    }
}

@Preview(showBackground = true)
@Composable
fun StateTest() {
    var total by remember { mutableStateOf(0) }

    Column {
        Text("The total is $total")
        Button(onClick = { total++ }) {
            Icon(imageVector = Icons.Default.Face, contentDescription = "Face")
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "Increment")
        }
    }
}