package com.cmpe277Hackathon.voicenavigator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmpe277Hackathon.voicenavigator.core.voice.impl.RealVoiceInputManager
import com.cmpe277Hackathon.voicenavigator.feature.dmv.presentation.screen.DmvAskScreen
import com.cmpe277Hackathon.voicenavigator.feature.dmv.presentation.viewmodel.DmvViewModelFactory
import com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.screen.EsgAskScreen
import com.cmpe277Hackathon.voicenavigator.feature.esg.presentation.viewmodel.EsgViewModelFactory
import com.cmpe277Hackathon.voicenavigator.feature.home.presentation.screen.HomeScreen
import com.cmpe277Hackathon.voicenavigator.feature.home.presentation.viewmodel.HomeViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val context = LocalContext.current

    // One voice manager per feature — each feature owns its own listening session
    val dmvVoiceManager = remember { RealVoiceInputManager(context) }
    val esgVoiceManager = remember { RealVoiceInputManager(context) }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ) {
        composable(AppDestinations.HOME) {
            HomeScreen(
                onNavigateToDmv = { navController.navigate(AppDestinations.DMV) },
                onNavigateToEsg = { navController.navigate(AppDestinations.ESG) },
                viewModel = viewModel<HomeViewModel>()
            )
        }
        composable(AppDestinations.DMV) {
            DmvAskScreen(
                viewModel = viewModel(factory = DmvViewModelFactory(dmvVoiceManager)),
                onBack = { navController.popBackStack() }
            )
        }
        composable(AppDestinations.ESG) {
            EsgAskScreen(
                viewModel = viewModel(factory = EsgViewModelFactory(esgVoiceManager)),
                onBack = { navController.popBackStack() }
            )
        }
    }
}
