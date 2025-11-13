package com.amangarg.lid.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.amangarg.lid.ui.home.MainViewModel
import com.amangarg.lid.ui.navigation.navgraph.generalQuestionsScreen
import com.amangarg.lid.ui.navigation.navgraph.homeScreen
import com.amangarg.lid.ui.navigation.navgraph.stateSelectionScreen
import com.amangarg.lid.ui.navigation.navgraph.viewPagerScreen

@Composable
fun LidNav(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel()
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Add App Top Bar
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            homeScreen(navController, mainViewModel)
            viewPagerScreen(navController)
            generalQuestionsScreen(navController, mainViewModel)
            stateSelectionScreen(navController, mainViewModel)
        }
    }
}

