package io.lab27.composemovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.lab27.composemovieapp.common.KEY_ROUTE
import io.lab27.composemovieapp.presentation.movie.MovieScreen
import io.lab27.composemovieapp.ui.theme.ComposeMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMovieAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Movie,
        BottomNavigationScreens.Tv,
        BottomNavigationScreens.My,
    )
    Scaffold(
        topBar = { MovieTopBar() },
        bottomBar = {MovieBottomNavigation(navController = navController, items = bottomNavigationItems)}
    ) {
        MainScreenNavigationConfigurations(navController = navController)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavigationScreens.Movie.route) {
        composable(BottomNavigationScreens.Movie.route) {
            MovieScreen()
        }
//        composable(BottomNavigationScreens.Pumpkin.route) {
//            ScaryScreen(ScaryAnimation.Pumpkin)
//        }
//        composable(BottomNavigationScreens.Ghost.route) {
//            ScaryScreen(ScaryAnimation.Ghost)
//        }
    }
}

@Composable
fun MovieTopBar(){
    Text(text = stringResource(id = R.string.app_name))
}

@Composable
private fun MovieBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon},
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}

sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Movie : BottomNavigationScreens("Movie", R.string.movie, Icons.Filled.Home)
    object Tv : BottomNavigationScreens("Tv", R.string.tv, Icons.Filled.ThumbUp)
    object My : BottomNavigationScreens("My", R.string.my, Icons.Filled.ThumbUp)
}