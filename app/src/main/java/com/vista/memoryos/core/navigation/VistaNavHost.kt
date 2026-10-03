
package com.vista.memoryos.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.vista.memoryos.feature.home.HomeScreen
import com.vista.memoryos.feature.onboarding.OnboardingScreen
import com.vista.memoryos.feature.profile.ProfileScreen
import com.vista.memoryos.feature.search.SearchScreen
import com.vista.memoryos.feature.splash.SplashScreen
import com.vista.memoryos.feature.timeline.TimelineScreen
import com.vista.memoryos.feature.auth.VerificationSuccessScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import io.github.jan.supabase.auth.status.SessionStatus
import com.vista.memoryos.feature.auth.AuthViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vista.memoryos.feature.auth.LoginScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun VistaNavHost(
    isAuthDeepLink: Boolean = false,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val sessionStatus by authViewModel.sessionStatus.collectAsState()
    val navController = rememberNavController()
    var authDeepLinkHandled by remember { mutableStateOf(false) }

    LaunchedEffect(isAuthDeepLink, sessionStatus, authDeepLinkHandled) {
        if (
            isAuthDeepLink &&
            !authDeepLinkHandled &&
            sessionStatus is SessionStatus.Authenticated
        ) {
            authDeepLinkHandled = true

            navController.navigate(Screen.VerificationSuccess.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    Scaffold(
        bottomBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            val showBottomBar = currentRoute in listOf(
                Screen.Home.route,
                Screen.Search.route,
                Screen.Timeline.route,
                Screen.Profile.route
            )

            if (showBottomBar) {
                NavigationBar {
                    bottomDestinations.forEach { destination ->
                        NavigationBarItem(
                            selected = currentRoute == destination.route,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(Screen.Home.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(destination.icon, null) },
                            label = { Text(destination.title) }
                        )
                    }
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(padding)
        ) {


            composable(Screen.Splash.route) {
                SplashScreen(
                    onLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    },
                    onRegister = {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    onLoginClick = {
                        navController.navigate(Screen.Login.route)
                    }
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onRegisterClick = {
                        navController.navigate(Screen.Onboarding.route)
                    }
                )
            }

            composable(Screen.VerificationSuccess.route) {
                VerificationSuccessScreen(
                    onContinue = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.VerificationSuccess.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Search.route) { SearchScreen() }
            composable(Screen.Timeline.route) { TimelineScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}