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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Room
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.components.CustomTextField
import com.saavatech.riserealestate.components.FeatureCardItem
import com.saavatech.riserealestate.components.NearByPropertyCard
import com.saavatech.riserealestate.components.PromotionCard
import com.saavatech.riserealestate.components.sectionTitles
import com.saavatech.riserealestate.data.remote.response.CategoryResponse
import com.saavatech.riserealestate.navigation.AppBottomSheet
import com.saavatech.riserealestate.navigation.BottomNavigation
import com.saavatech.riserealestate.navigation.Destinations
import com.saavatech.riserealestate.presentation.viewModel.HomeViewModel
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
    val viewModel: HomeViewModel = hiltViewModel()

    val categoryState = viewModel.categoriesState.value
    val nearbyState = viewModel.nearbyPropertiesState.value
    val categoryListState = viewModel.categoriesListState.value
    val nearbyListState = viewModel.nearbyPropertiesListState.value
    val featuredList = viewModel.featuredPropertiesListState.value

    val lazyState = rememberLazyListState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.getCategories()
        viewModel.getNearByProperties()
        viewModel.getFeaturedProperties(1, 6, true)
    }

    // ui satrts
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
                    .padding(10.dp),
        ) {
            LazyColumn(
                state = lazyState,
                modifier =
                    Modifier
                        .fillMaxHeight().padding(innerPadding),
            ) {
                item {
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

                        Spacer(modifier = Modifier.height(16.dp))
                        if (categoryState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                        LazyRow {
                            items(categoryListState) { category ->
                                PropertCategory(category = category) {
                                    navController.navigateTo("PropertyDetails/${category.id}")
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(28.dp)) }

                item {
                    Column(
                        modifier =
                        Modifier,
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

//                            featured properties  list
                            LazyRow {
                                items(featuredList) { featuredProperty ->
                                    FeatureCardItem(
                                        modifier = Modifier.width(300.dp),
                                        property = featuredProperty,
                                    ) {
                                        navController.navigateTo("PropertyDetails/${featuredProperty.id}")
                                    }
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

//                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                item {
                    Column {
                        if (nearbyState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }
                        LazyVerticalGrid(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.height(500.dp),
                            columns = GridCells.Fixed(2),
                        ) {
                            items(nearbyListState) { property ->
                                NearByPropertyCard(property = property, onClick = {})
//                                {
//                                    navController.navigateTo("PropertyDetails/${property.id}")
//                                }
                            }
                        }
                    }
                }
            }
        }

        // Call BottomSheet passing required parameters
        AppBottomSheet(
            sheetState = sheetState,
            showBottomSheet = showBottomSheet,
            onDismissRequest = { showBottomSheet = false },
            scope = scope,
        )
    }
}

// @Preview
@Composable
fun PropertCategory(
    category: CategoryResponse,
    clickAction: () -> Unit?,
) {
    Box(
        modifier =
            Modifier.background(
                shape = RoundedCornerShape(20.dp),
                color = inputBg,
            )
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
                .clickable {
                    clickAction.invoke()
                },
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            val painter =
                rememberAsyncImagePainter(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(category.image)
                            .decoderFactory(SvgDecoder.Factory()) // Configure SVG decoder
//                            .placeholder(R.drawable.placeholder_image) // Optional placeholder
//                            .error(R.drawable.error_image) // Optional error drawable
                            .build(),
                )
            Image(
                modifier =
                    Modifier
                        .size(35.dp),
                painter = painter,
                contentDescription = null,
            )
            Text(
                text = category.category,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
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
