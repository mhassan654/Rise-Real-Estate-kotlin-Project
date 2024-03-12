package com.saavatech.riserealestate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreens(
    val route: String,
    val icon: ImageVector,
) {
    data object Home : BottomScreens(route = "Home", icon = Icons.Filled.Home)

    data object Search : BottomScreens(route = "Search", icon = Icons.Filled.Search)

    data object FriendsList : BottomScreens(route = "friendslist", icon = Icons.Filled.Favorite)

    data object Profile : BottomScreens(route = "Profile", icon = Icons.Filled.AccountCircle)
}
