package com.saavatech.riserealestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.domain.use_case.AppEntryUseCases
import com.saavatech.riserealestate.navigation.MainNavigation
import com.saavatech.riserealestate.presentation.viewModel.MainViewModel
import com.saavatech.riserealestate.ui.theme.RiseRealEstateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        setContent {
            RiseRealEstateTheme {
                val screen = viewModel.startDestination
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainNavigation(navController = navController, startScreen = screen)
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
