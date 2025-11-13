package com.amangarg.lid.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amangarg.lid.ui.navigation.LidNav
import com.amangarg.lid.ui.theme.LidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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