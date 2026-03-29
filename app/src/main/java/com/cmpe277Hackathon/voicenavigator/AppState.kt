package com.cmpe277Hackathon.voicenavigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppState(val navController: NavHostController) {
    // TODO: add global state — mic permissions, connectivity, theme preference
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState = remember(navController) { AppState(navController) }
