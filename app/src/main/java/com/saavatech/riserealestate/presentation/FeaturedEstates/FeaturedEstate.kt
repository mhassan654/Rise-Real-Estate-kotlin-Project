package com.saavatech.riserealestate.presentation.FeaturedEstates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.ScreenShare
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.automirrored.outlined.SendToMobile
import androidx.compose.material.icons.automirrored.rounded.ForwardToInbox
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.saavatech.riserealestate.common.AppBar

@Composable
@Preview
fun FeaturedEstate() {
    Scaffold(
        topBar =
            {
                AppBar(
                    iconClickAction = {
//                    navController?.navigateUp()
                    },
                    title = null,
                    icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    actionIcon = Icons.AutoMirrored.Outlined.Send,
                )
            },
    ) { contentPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
        ) {
//
        }
    }
}
