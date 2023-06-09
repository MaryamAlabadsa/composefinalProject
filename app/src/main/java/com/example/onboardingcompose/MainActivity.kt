package com.example.onboardingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.myallwork.ViewModel.ApiServiceModel
import com.example.onboardingcompose.core.SharedViewModel
import com.example.onboardingcompose.navigation.SetupNavGraph
import com.example.onboardingcompose.ui.theme.OnBoardingComposeTheme
import com.example.onboardingcompose.viewmodel.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
     lateinit var sharedViewModel: SharedViewModel
    private val apiServiceModel: ApiServiceModel by viewModels(){
        SavedStateViewModelFactory(application, this)
    }

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        setContent {
            OnBoardingComposeTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                val navController2 = rememberNavController()
                SetupNavGraph(
                    navControllerMain = navController,
                    navControllerHome = navController2,
                    startDestination = screen,
                    apiServiceModel = apiServiceModel,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}
