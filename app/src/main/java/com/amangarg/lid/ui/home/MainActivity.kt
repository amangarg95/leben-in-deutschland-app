package com.amangarg.lid.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.amangarg.lid.data.QuestionUiState.Loading
import com.amangarg.lid.data.QuestionUiState.Success
import com.amangarg.lid.data.QuestionUiState.Error
import com.amangarg.lid.ui.navigation.LidNav
import com.amangarg.lid.ui.theme.LidTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.uiState.collectIn(this) { state ->
            when (state) {
                is Loading -> {
                    Toast.makeText(this, "Loading.....", Toast.LENGTH_SHORT).show()
                }
                is Success -> {
                     Toast.makeText(this, "Loading Completed", Toast.LENGTH_SHORT).show()
                }
                is Error -> {
                    Toast.makeText(this, "Error: ${state.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            LidTheme {
                LidNav()

//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    BundeslaenderGridScreen(Modifier.padding(innerPadding)) { selectedState ->
//                        Toast.makeText(
//                            this,
//                            "Selected: ${selectedState.german} / ${selectedState.english}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }

//                    ViewPagerScreen(sampleQuestions)
//                }
            }
        }
    }

    fun <T> Flow<T>.collectIn(
        lifecycleOwner: LifecycleOwner,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        collector: suspend (T) -> Unit
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(minActiveState) {
                collect { collector(it) }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LidTheme {
        Greeting("Android")
    }
}