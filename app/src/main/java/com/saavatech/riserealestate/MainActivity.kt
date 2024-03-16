package com.saavatech.riserealestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.domain.use_case.AppEntryUseCases
import com.saavatech.riserealestate.presentation.SplashViewModel
import com.saavatech.riserealestate.presentation.onBoarding.OnBoardingScreen
import com.saavatech.riserealestate.presentation.viewModel.OnBoardingViewModel
import com.saavatech.riserealestate.ui.theme.RiseRealEstateTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        lifecycleScope.launch {
            appEntryUseCases.readAppEntryUseCase().collect {
                Timber.tag("Test").d(it.toString())
            }
        }

        setContent {
            RiseRealEstateTheme {
                val onboardingViewModel: OnBoardingViewModel = hiltViewModel()
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                Timber.d("Start Screen: $screen")
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,
//                ) {
//                    MainNavigation(navController = navController, startScreen = Destinations.Home.route)
//                }
                OnBoardingScreen(event = onboardingViewModel::onEvent)
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
