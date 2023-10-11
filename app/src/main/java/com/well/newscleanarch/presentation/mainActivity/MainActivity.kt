package com.well.newscleanarch.presentation.mainActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.well.newscleanarch.data.local.NewsDao
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.domain.model.Source
import com.well.newscleanarch.domain.usecases.app_entry.AppEntryUseCases
import com.well.newscleanarch.presentation.navgraph.NavGraph
import com.well.newscleanarch.ui.theme.NewsCleanArchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var useCases: AppEntryUseCases

    @Inject
    lateinit var dao: NewsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {


            /* useCases.readAppEntry().collect {
                 Log.d("masuk gak", it.toString())
             }*/
        }

        setContent {
            NewsCleanArchTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}