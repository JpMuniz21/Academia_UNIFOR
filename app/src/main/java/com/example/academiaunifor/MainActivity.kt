package com.example.academiaunifor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.academiaunifor.navigation.AppNavGraph
import com.example.academiaunifor.ui.theme.AcademiaUniforTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AcademiaUniforTheme {
                AppNavGraph()
            }
        }
    }
}