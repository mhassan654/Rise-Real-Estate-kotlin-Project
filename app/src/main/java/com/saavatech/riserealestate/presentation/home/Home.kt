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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.ButtonTextComponent
import com.saavatech.riserealestate.common.CategoryButtonTextComponent
import com.saavatech.riserealestate.common.CustomOutlinedTextField
import com.saavatech.riserealestate.common.sectionTitles
import com.saavatech.riserealestate.navigation.BottomNavigation
import com.saavatech.riserealestate.navigation.BottomScreens
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg
import com.saavatech.riserealestate.ui.theme.primaryBackground1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val navController = rememberNavController()

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

                    CustomOutlinedTextField(painterResource(id = R.drawable.search1), "Search House, Apartment, etc")

                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow {
                        items(6) {
                            CategoryButtonTextComponent("All") {}
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
                            DiscountCard(
                                title = "sample",
                                subTitle = "All discount up to 60%",
                                backgroundResId = painterResource(id = R.drawable.image_25),
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }

                    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                        sectionTitles(title = "Featured Estates", title2 = "view all") {}

                        LazyRow {
                            items(4) {
                                featureCardItem()
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    }

                    sectionTitles(title = "Top Locations", title2 = "explore") {}
                    Spacer(modifier = Modifier.height(8.dp))
                    TopLocations()

                    sectionTitles(title = "Top Estate Agent", title2 = "explore") {}
                    TopAgents()

                    sectionTitles(title = "Explore Nearby Estates", null) {}

//                    LazyVerticalGrid(
//                        columns = GridCells.Adaptive(minSize = 128.dp)
//                    ) {
//                        items(4) {
                    NearbyCardItem()
//                        }
//                    }
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

@Composable
@Preview(showBackground = true)
fun HomePreview() {
    Home()
}

@Composable
fun featureCardItem() {
    Box(
        modifier =
            Modifier
                .clickable { }
                .width(300.dp)
                .height(190.dp)
                .background(color = inputBg, shape = RoundedCornerShape(20.dp)),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
        ) {
            Column {
                Box {
                    Image(
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.estates_card),
                        contentDescription = null,
                    )

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start,
                        modifier =
                            Modifier
                                .fillMaxHeight()
                                .padding(10.dp),
                    ) {
                        IconButton(
                            onClick = {},
                            modifier =
                                Modifier
                                    .background(color = GreenOne, shape = CircleShape)
                                    .padding(horizontal = 2.dp)
                                    .padding(vertical = 2.dp)
                                    .size(25.dp),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                tint = Color.White,
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                            )
                        }

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Apartment")
                        }
                    }
                }
            }

            Column(
                modifier =
                    Modifier
                        .padding(10.dp)
                        .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
//                    modifier = Modifier.height(40.dp),
                    text = "Sky Dandelions Apartment",
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Left,
                )

                Text(
                    text =
                        buildAnnotatedString {
                            withStyle(
                                style =
                                    SpanStyle(
                                        color = TextColorOne,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight(500),
                                    ),
                            ) {
                                append("$ 290")
                            }
                            append("")
                            withStyle(
                                style =
                                    SpanStyle(
                                        color = TextColorBold,
                                        fontWeight = FontWeight(700),
                                        fontSize = 10.sp,
                                    ),
                            ) {
                                append("/month")
                            }
                        },
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    color = TextColorBold,
                )
//
            }
//
        }

//
    }
}

@Composable
fun DiscountCard(
    backgroundResId: Painter,
    title: String,
    subTitle: String,
) {
    Surface(
        modifier =
            Modifier
                .width(260.dp)
                .height(180.dp),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp, bottomEnd = 28.dp, bottomStart = 0.dp),
    ) {
        Box {
            Image(
                painter = backgroundResId,
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxSize(),
                contentScale = ContentScale.Fit,
            )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(vertical = 30.dp, horizontal = 20.dp),
            ) {
                TextOverlayTitle(text = title)
                Spacer(modifier = Modifier.height(16.dp))
                TextOverlaySubTitle(text = subTitle)
            }

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(0.dp),
            ) {
                Row(verticalAlignment = Alignment.Bottom) {
                    IconButton(
                        onClick = {},
                        modifier =
                            Modifier
                                .background(
                                    primaryBackground1,
                                    shape =
                                        RoundedCornerShape(
                                            topStart = 0.dp,
                                            topEnd = 25.dp,
                                            bottomEnd = 0.dp,
                                            bottomStart = 0.dp,
                                        ),
                                )
                                .padding(horizontal = 10.dp)
                                .padding(vertical = 2.dp)
                                .width(80.dp)
                                .height(60.dp),
                    ) {
                        Icon(
                            tint = Color.White,
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TextOverlayTitle(text: String) {
    Column(
        modifier =
        Modifier,
//                .fillMaxSize()
//                .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Left,
        )
    }
}

@Composable
private fun TextOverlaySubTitle(text: String) {
    Column(
        modifier =
        Modifier,
//                .fillMaxSize()
//                .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            fontSize = 16.sp,
            fontWeight = FontWeight(300),
            textAlign = TextAlign.Left,
        )
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

@Composable
fun NearbyCardItem() {
    Box(
        modifier =
            Modifier
                .clickable { }
                .width(190.dp)
                .height(300.dp)
                .background(color = inputBg, shape = RoundedCornerShape(22.dp)),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.estates_card),
                contentDescription = null,
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(10.dp),
            ) {
                IconButton(
                    onClick = {},
                    modifier =
                        Modifier
                            .background(color = GreenOne, shape = CircleShape)
                            .padding(horizontal = 2.dp)
                            .padding(vertical = 2.dp)
                            .size(25.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                    )
                }

                Text(
                    text =
                        buildAnnotatedString {
                            withStyle(
                                style =
                                    SpanStyle(
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight(400),
                                    ),
                            ) {
                                append("$ 290")
                            }
                            append("")
                            withStyle(
                                style =
                                    SpanStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight(300),
                                        fontSize = 10.sp,
                                    ),
                            ) {
                                append("/month")
                            }
                        },
                    modifier =
                        Modifier
                            .widthIn(max = 150.dp)
                            .background(color = primaryBackground1, shape = RoundedCornerShape(11.dp))
                            .padding(4.dp),
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.End,
                    color = Color.White,
                )

                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        Column(
            modifier =
                Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomStart),
            // Align to the bottom
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "Sky Dandelions Apartment",
                style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Left,
            )

            Row(modifier = Modifier.padding(6.dp)) {
                Icon(
                    tint = primaryBackground1,
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Rounded.Room,
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Old Kampala",
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = TextColorOne,
                )
            }
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
