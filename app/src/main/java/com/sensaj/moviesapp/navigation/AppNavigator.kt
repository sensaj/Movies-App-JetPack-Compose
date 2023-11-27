package com.sensaj.moviesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sensaj.moviesapp.ui.views.screens.CharacterViewScreen
import com.sensaj.moviesapp.ui.views.screens.HomePage
import com.sensaj.moviesapp.ui.views.screens.MovieDescrScreen

@Composable
fun AppScreensNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN ){
        composable(Routes.HOME_SCREEN){
            HomePage(navigator = navController)
        }
        composable(Routes.MOVIE_DESCR_SCREEN){
            MovieDescrScreen(navigator = navController)
        }
        composable(Routes.CHARACTER_SCREEN){
            CharacterViewScreen(navigator = navController)
        }
    }
}