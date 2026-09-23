package com.vista.memoryos.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomDestination(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val bottomDestinations = listOf(
    BottomDestination("Home", Icons.Outlined.Home, Screen.Home.route),
    BottomDestination("Search", Icons.Outlined.Search, Screen.Search.route),
    BottomDestination("Timeline", Icons.Outlined.DateRange, Screen.Timeline.route),
    BottomDestination("Profile", Icons.Outlined.Person, Screen.Profile.route)
)