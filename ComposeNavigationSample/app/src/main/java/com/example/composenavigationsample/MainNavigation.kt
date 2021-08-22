package com.example.composenavigationsample

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalAnimationApi
@Composable
fun MainNavigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route,
            exitTransition = { _, _ ->
                Log.d("MainScreen", "exitTransition")
                slideOutHorizontally()
            },
        ) {
            MainScreen(navController = navController)
        }
        // /{name} は必須アラメータの指定方法
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    nullable = false
                }
            ),
            popExitTransition = { _, _ ->
                Log.d("DetailScreen", "popExitTransition")
                slideOutHorizontally(1000)
            }
        ) { entry ->
            DetailScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@ExperimentalAnimationApi
private fun slideOutHorizontally(
    targetOffsetX: Int = -1000,
    durationMillis: Int = 500 // DefaultDurationMillis
): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { targetOffsetX },
        animationSpec = tween(durationMillis)
    ) + fadeOut(animationSpec = tween(durationMillis))
}

@ExperimentalAnimationApi
private fun slideInHorizontally(
    initialOffsetX: Int = 1000,
    durationMillis: Int = 500 // DefaultDurationMillis
): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { initialOffsetX },
        animationSpec = tween(durationMillis)
    ) + fadeIn(animationSpec = tween(durationMillis))
}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var isEnableBtn by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        TextField(value = text, onValueChange = {
            text = it
            isEnableBtn = it.isNotEmpty()
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DetailScreen.withArgs(text))
            }, modifier = Modifier.align(Alignment.End),
            enabled = isEnableBtn
        ) {
            Text(text = "to Detail Screen.")
        }

    }
}

@Composable
fun DetailScreen(name: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Hello, $name")
    }
}