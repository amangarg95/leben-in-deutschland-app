package com.amangarg.lid.ui.home

import StateWiseOption
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.amangarg.lid.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onGeneralQuestionsOptionClick: () -> Unit,
    onStateWiseOptionClick: () -> Unit
) {

    Column {

        GeneralQuestionsOption(
            image = painterResource(id = R.drawable.ic_germany),
            title = "General Questions",
            onClick = { onGeneralQuestionsOptionClick() }
        )
        StateWiseOption(
            image = painterResource(id = R.drawable.ic_german_states),
            title = "State-Wise Questions",
            onClick = { onStateWiseOptionClick() }
        )
    }


}
