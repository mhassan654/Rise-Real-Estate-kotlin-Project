package com.saavatech.riserealestate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTopAppBar(
    icon: ImageVector,
    actionIcon: ImageVector?,
    iconClickAction: () -> Unit,
) {
    Box {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent, // Set background color to transparent
            elevation = 0.dp, // Remove elevation to make it appear on top
            contentColor = MaterialTheme.colors.primary, // Set content color
            title = {
            },
            navigationIcon = {
                RoundedIconButton(
                    modifier = Modifier.background(color = Color.White),
                    icon = icon,
                    onClick = { iconClickAction.invoke() },
                    contentDescription = "Add to favorites",
                )
            },
            actions = {
                if (actionIcon != null) {
                    RoundedIconButton(
                        icon = actionIcon,
                        onClick = { iconClickAction.invoke() },
                        contentDescription = "Share to others",
                    )
                }
            },
        )
    }
}
