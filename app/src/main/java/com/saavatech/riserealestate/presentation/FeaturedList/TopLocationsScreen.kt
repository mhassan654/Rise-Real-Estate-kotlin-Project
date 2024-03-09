package com.saavatech.riserealestate.presentation.FeaturedList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.common.AppBar
import com.saavatech.riserealestate.common.CustomGridView
import com.saavatech.riserealestate.common.TopLocationCard

@Composable
fun TopLocationsScreen(navController: DestinationsNavigator) {
    Scaffold(
        topBar =
            {
                AppBar(
                    iconClickAction = {
                        navController.navigateUp()
                    },
                    title = null,
                    icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    actionIcon = null,
                )
            },
    ) { contentPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
        ) {
            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(15.dp),
            ) {
                item {
                Column {
                    Text(
                        text = "Top Villa",
                        fontSize = 25.sp,
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(13.dp))

                    Text(
                        text = "Find the best recommendations place to live",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    CustomGridView(
                        content = {
                            TopLocationCard()
                        },
                        repeat = 10,
                    )
                }


            }
        }
    }
}
