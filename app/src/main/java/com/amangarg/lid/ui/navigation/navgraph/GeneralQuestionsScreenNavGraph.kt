package com.amangarg.lid.ui.navigation.navgraph

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amangarg.lid.ui.general.GeneralQuestionsScreen
import com.amangarg.lid.ui.home.MainViewModel

fun NavGraphBuilder.generalQuestionsScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    composable(
        route = "general_questions"
    ) {
        GeneralQuestionsScreen(Modifier)
    }
}