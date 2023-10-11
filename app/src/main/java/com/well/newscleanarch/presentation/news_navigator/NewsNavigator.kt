package com.well.newscleanarch.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.well.newscleanarch.R
import com.well.newscleanarch.domain.model.Article
import com.well.newscleanarch.presentation.bookmark.BookmarkScreen
import com.well.newscleanarch.presentation.bookmark.BookmarkViewModel
import com.well.newscleanarch.presentation.details.DetailsEvent
import com.well.newscleanarch.presentation.details.DetailsScreen
import com.well.newscleanarch.presentation.details.DetailsViewModel
import com.well.newscleanarch.presentation.home.HomeScreen
import com.well.newscleanarch.presentation.home.HomeViewModel
import com.well.newscleanarch.presentation.navgraph.Route
import com.well.newscleanarch.presentation.news_navigator.components.BottomNavigationItem
import com.well.newscleanarch.presentation.news_navigator.components.NewsBottomNavigation
import com.well.newscleanarch.presentation.search.SearchScreen
import com.well.newscleanarch.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {


    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_public, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookmarkScreen.route
    }

    selectedItem = remember(key1 = backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                Route.HomeScreen.route
                            )
                            1 -> navigateToTab(
                                navController = navController,
                                Route.SearchScreen.route
                            )
                            2 -> navigateToTab(
                                navController = navController,
                                Route.BookmarkScreen.route
                            )
                        }
                    },
                )
            }
        }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.allNews.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(navController = navController, article = article)
                    })

            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewMode: BookmarkViewModel = hiltViewModel()
                val state = viewMode.state.value
                BookmarkScreen(state = state, navigateToDetails = { article ->
                    navigateToDetails(navController = navController, article = article)
                })
            }

        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreenRoute ->
            popUpTo(homeScreenRoute) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}