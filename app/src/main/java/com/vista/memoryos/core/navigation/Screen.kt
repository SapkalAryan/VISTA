package com.vista.memoryos.core.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")

    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Timeline : Screen("timeline")
    data object Profile : Screen("profile")
}