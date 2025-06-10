package com.example.android_multimodular_architecture_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_multimodular_architecture_practice.ui.theme.AndroidmultimodulararchitecturepracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidmultimodulararchitecturepracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Base Url : ${BuildConfig.BASE_URL}",
        )
        Text(
            text = "DB Version : ${BuildConfig.DB_VERSION}",
        )
        Text(
            text = "Can Clear Cache : ${BuildConfig.CAN_CLEAR_CACHE}",
        )
        Text(
            text = "Map Key : ${BuildConfig.MAP_KEY}",
        )
    }

}