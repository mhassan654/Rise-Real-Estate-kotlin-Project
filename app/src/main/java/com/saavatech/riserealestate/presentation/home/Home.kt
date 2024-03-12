package com.saavatech.riserealestate.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Room
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.ButtonTextComponent
import com.saavatech.riserealestate.common.CategoryButtonTextComponent
import com.saavatech.riserealestate.common.CustomTextField
import com.saavatech.riserealestate.common.FeatureCardItem
import com.saavatech.riserealestate.common.PromotionCard
import com.saavatech.riserealestate.common.VerticalPropertyCard
import com.saavatech.riserealestate.common.sectionTitles
import com.saavatech.riserealestate.navigation.BottomNavigation
import com.saavatech.riserealestate.navigation.BottomScreens
import com.saavatech.riserealestate.navigation.Destinations
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg
import com.saavatech.riserealestate.ui.theme.primaryBackground1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: DestinationsNavigator,
    navigationCallback: (Int) -> Unit,
) {
//    val navController = rememberNavController()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val items =
        listOf(
            BottomScreens.Home,
            BottomScreens.FriendsList,
        )
    Scaffold(
        topBar = {
            TobBar { showBottomSheet = true }
        },
        bottomBar = {
            BottomNavigation()
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                ) {
                    Text(
                        text =
                            buildAnnotatedString {
                                withStyle(
                                    style =
                                        SpanStyle(
                                            color = TextColorOne,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight(400),
                                        ),
                                ) {
                                    append("Hey,")
                                }
                                append(" ")
                                withStyle(
                                    style =
                                        SpanStyle(
                                            color = TextColorBold,
                                            fontWeight = FontWeight(700),
                                            fontSize = 20.sp,
                                        ),
                                ) {
                                    append("Hassan Saava!")
                                }
                            },
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        color = TextColorBold,
                    )

                    Text(
                        modifier =
                            Modifier
                                .padding(top = 20.dp),
                        text = "Let's start exploring",
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        color = TextColorOne,
                    )

                    CustomTextField(painterResource(id = R.drawable.search1), "Search House, Apartment, etc")
//                    CustomOutlinedTextField(painterResource(id = R.drawable.search1), "Search House, Apartment, etc")

                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow {
                        items(6) {
                            CategoryButtonTextComponent("All") {
                                navController.navigateTo(Destinations.EstateByCategory.route)
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))
                Column(
                    modifier =
                        Modifier
                            .verticalScroll(rememberScrollState()),
                ) {
                    LazyRow {
                        items(4) {
                            PromotionCard(
                                title = "sample",
                                subTitle = "All discount up to 60%",
                                backgroundResId = painterResource(id = R.drawable.image_25),
                                width = 260.dp,
                                onClickAction = { navController.navigateTo(Destinations.PromotionScreen.route) },
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                        sectionTitles(title = "Featured Estates", title2 = "view all") {
                            navController.navigateTo(Destinations.FeaturedEstate.route)
                        }

                        LazyRow {
                            items(4) {
                                FeatureCardItem(
                                    modifier = Modifier.width(300.dp),
                                    title = "Sky Dandelions Apartment",
                                    imageTitle = "Old kampala",
                                    navigationCallback,
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }

                    sectionTitles(title = "Top Locations", title2 = "explore") {
                        navController.navigateTo(Destinations.TopLocations.route)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TopLocations()

                    sectionTitles(title = "Top Estate Agent", title2 = "explore") {}
                    TopAgents()

                    sectionTitles(title = "Explore Nearby Estates", null) {}

                    // Explore nearby estates

                    repeat(4) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            repeat(2) {
                                VerticalPropertyCard(navigationCallback)
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 0.dp, bottomStart = 0.dp),
//                scrimColor = MaterialTheme.colorScheme.primary,
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
            ) {
                Column(
                    modifier =
                        Modifier
                            .padding(12.dp)
                            .clickable {
                                scope
                                    .launch { sheetState.hide() }
                                    .invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                    }
                            },
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            color = TextColorOne,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(500),
                            text = "Select Location",
                        )

                        TextButton(
                            colors =
                                ButtonDefaults.buttonColors(
                                    MaterialTheme.colorScheme.primary,
                                ),
                            onClick = { /*TODO*/ },
                        ) {
                            Text(
                                text = "Edit",
                            )
//
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        rowButton(
                            location = "Old Kampala primary school , sir appolo road",
                            bgColor = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        rowButton(
                            location = "Old Kampala primary school , sir appolo road",
                            bgColor = Color.White,
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        ButtonTextComponent(value = "Choose Location", width = 300.dp, clickAction = {})
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun bottomSheet() {
    Column(
        modifier =
            Modifier
                .padding(12.dp)
                .clickable {
//                                scope
//                                    .launch { sheetState.hide() }
//                                    .invokeOnCompletion {
//                                        if (!sheetState.isVisible) {
//                                            showBottomSheet = false
//                                        }
//                                    }
                },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
                text = "Select Location",
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.width(8.dp))

            TextButton(
                onClick = { /* TODO */ },
                colors =
                    ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White,
                    ),
            ) {
                Text(
                    text = "Edit",
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            rowButton(
                location = "Old Kampala primay school , sir appolo road",
                bgColor = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(10.dp))
            rowButton(
                location = "Old Kampala primay school , sir appolo road",
                bgColor = Color.White,
            )
            Spacer(modifier = Modifier.height(10.dp))

            ButtonTextComponent(value = "Choose Location", width = 300.dp, clickAction = {})
        }
        Spacer(modifier = Modifier.height(20.dp))

//
    }
}

@Composable
fun rowButton(
    bgColor: Color,
    location: String,
//    border:
) {
    Box(
        modifier =
            Modifier.padding(8.dp).fillMaxWidth().heightIn(90.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                .background(
                    color = bgColor,
                    RoundedCornerShape(25.dp),
                ),
    ) {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.clip(CircleShape).background(color = colorResource(id = R.color.lightPrimary)).padding(16.dp),
            ) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Rounded.Room,
                    contentDescription = null,
                    modifier = Modifier.clip(CircleShape),
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = location)
        }
    }
}

// home screen top bar section
@Composable
fun TobBar(openBottomSheetClick: () -> Unit) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier =
                    Modifier
                        .clickable { openBottomSheetClick.invoke() }
                        .background(inputBg, shape = RoundedCornerShape(30.dp)),
            ) {
                Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                    Icon(
                        tint = primaryBackground1,
                        modifier = Modifier.size(17.dp),
                        imageVector = Icons.Rounded.Room,
                        contentDescription = null,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Old Kampala",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = TextColorOne,
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.size(17.dp),
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null,
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    modifier =
                        Modifier
                            .clip(shape = CircleShape)
                            .border(
                                BorderStroke(1.dp, Color.Green),
                                shape = CircleShape,
                            ),
                    onClick = { },
                ) {
                    Icon(painter = painterResource(id = R.drawable.bell), null)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    modifier =
                        Modifier
                            .size(50.dp)
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                                shape = CircleShape,
                            )
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun TopLocations() {
    LazyRow {
        items(8) {
            Row(
                modifier =
                    Modifier
                        .background(inputBg, shape = RoundedCornerShape(32.dp))
                        .padding(7.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier =
                        Modifier
                            .size(60.dp)
                            .border(BorderStroke(1.dp, inputBg), shape = CircleShape)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Saava",
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun TopAgents() {
    LazyRow {
        items(8) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier =
                        Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Saava",
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            // Sheet content
            Button(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }) {
                Text("Hide bottom sheet")
            }
        }
    }
}
