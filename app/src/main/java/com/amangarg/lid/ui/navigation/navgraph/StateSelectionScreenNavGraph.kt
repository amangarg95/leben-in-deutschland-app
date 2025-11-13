package com.amangarg.lid.ui.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.amangarg.lid.ui.home.MainViewModel
import com.amangarg.lid.ui.states.StateSelectionScreen

fun NavGraphBuilder.stateSelectionScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {

    composable(
        route = "state_selection"
    ) {
        StateSelectionScreen()
    }
}