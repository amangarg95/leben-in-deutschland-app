package com.amangarg.lid.ui.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amangarg.lid.data.QuestionsItemDto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.collections.lastIndex

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerScreen(
    questions: List<QuestionsItemDto>?
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        HorizontalPager(
            count = questions?.size ?: 0,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            questions?.let { MCQCard(it[page]) }
        }
        Spacer(modifier = Modifier.height(16.dp))
//        HorizontalPagerIndicator(
//            pagerState = pagerState,
//            activeColor = MaterialTheme.colors.primary
//        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage((pagerState.currentPage-1).coerceAtLeast(0)) } },
                enabled = pagerState.currentPage > 0
            ) {
                Text("Previous")
            }
            Button(
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage((pagerState.currentPage+1).coerceAtMost(questions!!.lastIndex)) } },
                enabled = pagerState.currentPage < questions!!.lastIndex
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun MCQCard(question: QuestionsItemDto) {
    var selectedOption by remember { mutableStateOf(-1) }

    val options = listOf(question.a, question.b, question.c, question.d)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Aufgabe ${question.id}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = question.question,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        options.forEachIndexed { idx, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .toggleable(
                        value = selectedOption == idx,
                        onValueChange = { selectedOption = idx }
                    )
            ) {
                Checkbox(
                    checked = selectedOption == idx,
                    onCheckedChange = { selectedOption = idx }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option, fontSize = 16.sp)
            }
        }
    }
}
