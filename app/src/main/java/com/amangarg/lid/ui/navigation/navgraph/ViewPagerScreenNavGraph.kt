package com.amangarg.lid.ui.navigation.navgraph

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.amangarg.lid.ui.states.ViewPagerScreen
import com.amangarg.lid.util.loadQuestionsFromAssets

fun NavGraphBuilder.viewPagerScreen(
    navController: NavController
) {
    composable(
        route = "view_pager"
    ) {
        val questionsWrapper = loadQuestionsFromAssets(LocalContext.current, "question_en_only.json")
        val questionsList = questionsWrapper

        ViewPagerScreen(questionsList)
    }
}