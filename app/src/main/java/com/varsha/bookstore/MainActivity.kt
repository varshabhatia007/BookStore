package com.varsha.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.varsha.bookstore.component.bookdetail.BookDetailScreen
import com.varsha.bookstore.component.booklist.HomeScreen
import com.varsha.bookstore.ui.navigation.Screen
import com.varsha.bookstore.ui.theme.BookStoreTheme
import com.varsha.bookstore.viewmodel.BookStoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookStoreTheme {
                val navController = rememberNavController()
                val viewModel: BookStoreViewModel = hiltViewModel()

                // Navigation component for handling the screens navigation
                NavigationComponent(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    viewModel: BookStoreViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Book Information - Home Screen
        composable(Screen.Home.route) {
            val scope = rememberCoroutineScope()
            scope.launch {
                viewModel.getBooksData()
            }
            HomeScreen(
                viewModel = viewModel,
                showDetailScreen = {
                    navController.navigate(Screen.Detail.createRoute(it))
                }
            )
        }

        // Book Detail Information - Detail Screen
        composable(Screen.Detail.route) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getString("bookId")
            bookId?.let {
                val scope = rememberCoroutineScope()
                scope.launch {
                    viewModel.getBookDataFromId(bookId.toInt())
                }
                BookDetailScreen(
                    navigateUp = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }
        }
    }
}