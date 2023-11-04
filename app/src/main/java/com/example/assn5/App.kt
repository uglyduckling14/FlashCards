package com.example.assn5

import androidx.compose.runtime.Composable
import com.example.assn5.ui.navigation.RootNavigation
import com.example.assn5.ui.theme.Assn5Theme

@Composable
fun App(){
    Assn5Theme {
        RootNavigation()
    }
}