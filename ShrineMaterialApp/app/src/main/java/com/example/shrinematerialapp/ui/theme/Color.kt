package com.example.shrinematerialapp.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material.lightColors

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val ShrinePink10 = Color(0xfffffbfa)
val ShrinePink50 = Color(0xfffeeae6)
val ShrinePink100 = Color(0xfffedbd0)
val ShrinePink300 = Color(0xfffff0ea)
val ShrinePink500 = Color(0xfffbb8ac)
val ShrinePink900 = Color(0xff442c2e)

internal val ShrineLightColorPalette = lightColors(
    primary = ShrinePink100,
    primaryVariant = ShrinePink500,
    secondary = ShrinePink50,
    background = ShrinePink100,
    surface = ShrinePink10,
    error = Color(0xffc5032b),
    onPrimary = ShrinePink900,
    onSecondary = ShrinePink900,
    onBackground = ShrinePink900,
    onSurface = ShrinePink900,
    onError = ShrinePink10
)