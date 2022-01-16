package com.example.shrinematerialapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shrinematerialapp.R
import com.example.shrinematerialapp.ui.theme.ShrineMaterialAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Backdrop() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_menu_cut_24px),
                        contentDescription = "Menu Icon",
                        modifier = Modifier.clickable {
                            scope.launch {
                                if (scaffoldState.isConcealed) {
                                    scaffoldState.reveal()
                                } else {
                                    scaffoldState.conceal()
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Shrine".uppercase(),
                        style = MaterialTheme.typography.subtitle1,
                        fontSize = 17.sp
                    )
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
            BackdropMenuItems()
        },
        frontLayerShape = MaterialTheme.shapes.large,
        frontLayerElevation = 16.dp
    )
}

@Composable
private fun BackdropMenuItems() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuItem("Feature01")
        MenuItem("Feature02")
        MenuItem("Feature03")
        MenuItem("Feature04")
    }
}

@Composable
fun MenuItem(text: String = "Menu Item") {
    Text(text = text.uppercase(), style = MaterialTheme.typography.subtitle1)
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