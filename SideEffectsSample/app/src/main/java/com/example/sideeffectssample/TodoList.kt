package com.example.sideeffectssample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TodoList(
    highPriorityKeywords: List<String> = listOf("Review", "Unblock"),
    modifier: Modifier
) {
    val todoTasks = remember { mutableStateListOf<String>() }
    // Calculate high priority tasks only when the todoTasks or highPriorityKeywords
    // change, not on every recomposition
    val highPriorityTasks by remember(highPriorityKeywords) {
        derivedStateOf { todoTasks.filter { highPriorityKeywords.contains(it) } }
    }

    TodoListStateless(modifier, todoTasks, highPriorityTasks)
}

@Composable
private fun TodoListStateless(
    modifier: Modifier,
    todoTasks: SnapshotStateList<String>,
    highPriorityTasks: List<String>
) {
    Column(modifier) {
        Row {
            Button(onClick = { todoTasks.add("Review") }, modifier = Modifier.weight(1f)) {
                Text(text = "Review")
            }
            Button(onClick = { todoTasks.add("Unblock") }, modifier = Modifier.weight(1f)) {
                Text(text = "Unblock")
            }
            Button(onClick = { todoTasks.add("Compose") }, modifier = Modifier.weight(1f)) {
                Text(text = "Compose")
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Text(text = "highPriorityTasks", color = Color.Green) }
            items(highPriorityTasks) { item -> Text(text = item) }
            item { Text(text = "todoTasks", color = Color.Green) }
            items(todoTasks) { item -> Text(text = item) }
        }
    }
}