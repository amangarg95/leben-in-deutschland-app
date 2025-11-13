package com.amangarg.lid.ui.states

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amangarg.lid.util.BundeslandMapper

@SuppressLint("LocalContextResourcesRead")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StateSelectionScreen(
    modifier: Modifier = Modifier,
    states: List<BundeslandMapper.StateInfo> = BundeslandMapper.states,
    onItemClick: (BundeslandMapper.StateInfo) -> Unit = {}
) {
    // Two columns staggered grid
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(states.size) { index ->
            val state = states[index]

            Box(
                modifier = Modifier
                    // Size of each cube box - square shape
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryFixedDim)
                    .clickable { onItemClick(state) }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val context = LocalContext.current
                    val drawableId = context.resources.getIdentifier(
                        state.flagResourceName, // e.g. "flag_baden_wuerttemberg"
                        "drawable",
                        context.packageName
                    )
                    if (drawableId != 0) {
                        Image(
                            painter = painterResource(drawableId),
                            contentDescription = "Flag of ${state.german}",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        // Placeholder box if not found
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .background(Color.Gray, RoundedCornerShape(8.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "?",
                                color = Color.White,
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    }

                    Text(
                        text = state.german,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.english,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
