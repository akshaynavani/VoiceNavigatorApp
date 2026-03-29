package com.cmpe277Hackathon.voicenavigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.cmpe277Hackathon.voicenavigator.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val appState = rememberAppState()
                    AppNavGraph(navController = appState.navController)
                }
            }
        }
    }
}
