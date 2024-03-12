package com.saavatech.riserealestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.navigation.Destinations
import com.saavatech.riserealestate.navigation.MainNavigation
import com.saavatech.riserealestate.presentation.SplashViewModel
import com.saavatech.riserealestate.ui.theme.RiseRealEstateTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            RiseRealEstateTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                Timber.d("Start Screen: $screen")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainNavigation(navController = navController, startScreen = Destinations.Register.route)
                }
            }
        }
    }
}

class DestinationsNavigator(private val navHostController: NavHostController) {
    fun navigateTo(destination: String) {
        navHostController.navigate(destination)
    }

    fun navigateUp() {
        navHostController.popBackStack()
    }
}
