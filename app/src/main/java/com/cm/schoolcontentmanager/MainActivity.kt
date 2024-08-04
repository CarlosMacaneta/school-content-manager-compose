package com.cm.schoolcontentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.cm.schoolcontentmanager.login.LoginScreen
import com.cm.schoolcontentmanager.ui.theme.SchoolContentManagerComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolContentManagerComposerTheme {
                LoginScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
