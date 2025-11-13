package com.amangarg.lid.ui.navigation.navgraph

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amangarg.lid.ui.home.HomeScreen
import com.amangarg.lid.ui.home.MainViewModel

fun NavGraphBuilder.homeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    composable(
        route = "home"
    ) {
        HomeScreen(
            Modifier,
            onGeneralQuestionsOptionClick = { navController.navigate("view_pager") },
            onStateWiseOptionClick = { navController.navigate("state_selection") }
        )
    }
}