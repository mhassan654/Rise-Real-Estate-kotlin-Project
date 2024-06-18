package com.saavatech.riserealestate.presentation.FeaturedList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.components.AppBar
import com.saavatech.riserealestate.components.CollageImage
import com.saavatech.riserealestate.components.CustomTextField
import com.saavatech.riserealestate.components.FeatureCardItem
import com.saavatech.riserealestate.components.VerticalPropertyCard
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.presentation.viewModel.HomeViewModel
import com.saavatech.riserealestate.ui.theme.Purple80
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg

@Suppress("ktlint:standard:function-naming")
// fun FeaturedEstate() {
@Composable
fun FeaturedEstate(navController: DestinationsNavigator) {
    val viewModel: HomeViewModel = hiltViewModel()
    val featuredList = viewModel.featuredPropertiesListState.value
    val categoryState = viewModel.loadingState.value
    var switchViewStyle by remember { mutableStateOf(false) }
    val lazyState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        viewModel.getFeaturedProperties(1, 10, true)
    }
    Scaffold(
        topBar =
            {
                AppBar(
                    iconClickAction = {
                        navController.navigateUp()
                    },
                    title = null,
                    icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    actionIcon = Icons.AutoMirrored.Filled.CompareArrows,
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
                state = lazyState,
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(15.dp),
            ) {
                item {
                    CollageImage(
                        modifier = Modifier,
                        image1 = painterResource(id = R.drawable.image_28),
                        image2 = painterResource(id = R.drawable.image_29),
                        image3 = painterResource(id = R.drawable.image_27),
                    )
                }

                item { Spacer(modifier = Modifier.height(15.dp)) }

                item {
                    // header
                    Column {
                        Text(
                            text = "Featured Estates",
                            fontSize = 25.sp,
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(13.dp))

                        Text(
                            text = "Our recommended real estates exclusive for you",
                            fontSize = 15.sp,
                            fontWeight = FontWeight(300),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                }
                // Search field
                item {
                    CustomTextField(painterResource(id = R.drawable.search1), "Search House, Apartment, etc")
                }

                item {
                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        Text(
                            text =
                                buildAnnotatedString {
                                    withStyle(
                                        style =
                                            SpanStyle(
                                                color = TextColorOne,
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight(700),
                                            ),
                                    ) {
                                        append(featuredList.size.toString())
                                    }
                                    append(" ")
                                    withStyle(
                                        style =
                                            SpanStyle(
                                                color = TextColorBold,
                                                fontWeight = FontWeight(400),
                                                fontSize = 22.sp,
                                            ),
                                    ) {
                                        append("estates")
                                    }
                                },
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Start,
                            color = TextColorBold,
                        )

                        Box(
                            modifier =
                                Modifier
                                    .background(color = inputBg, shape = RoundedCornerShape(23.dp)),
                        ) {
                            Row(
                                modifier =
                                    Modifier
                                        .padding(8.dp),
                                horizontalArrangement = Arrangement.End,
                            ) {
                                Box(
                                    modifier =
                                        Modifier
                                            .background(
                                                color = if (switchViewStyle) Color.White else Color.Transparent,
                                                shape = RoundedCornerShape(10.dp),
                                            ).padding(horizontal = 5.dp, vertical = 2.dp),
                                ) {
                                    IconButton(
                                        onClick = { switchViewStyle = true },
                                        modifier = Modifier.size(15.dp),
                                        content = {
                                            Icon(
                                                tint = if (switchViewStyle) MaterialTheme.colorScheme.primary else Purple80,
                                                painter = painterResource(id = R.drawable.grid_view),
                                                contentDescription = null,
                                            )
                                        },
                                    )
                                }
                                Spacer(modifier = Modifier.width(20.dp))

                                Box(
                                    modifier =
                                        Modifier
                                            .background(
                                                color = if (!switchViewStyle) Color.White else Color.Transparent,
                                                shape = RoundedCornerShape(15.dp),
                                            ).padding(horizontal = 4.dp, vertical = 2.dp),
                                ) {
                                    IconButton(
                                        onClick = { switchViewStyle = false },
                                        modifier = Modifier.size(16.dp),
                                        content = {
                                            Icon(
                                                tint = if (!switchViewStyle) MaterialTheme.colorScheme.primary else Purple80,
                                                painter = painterResource(id = R.drawable.baseline_view),
                                                contentDescription = null,
                                            )
                                        },
                                    )
                                }
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(25.dp)) }

                // Featured cards
                item {
                    if (switchViewStyle) {
                        GridView(featuredList, navController)
                    } else {
                        ListView(featuredList, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun GridView(
    featuredItems: List<Property>,
    navController: DestinationsNavigator,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        featuredItems.chunked(2).forEach { chunk ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                chunk.firstOrNull()?.let { item ->
                    Column(modifier = Modifier.weight(1f)) {
                        // Adjust weight for desired ratio
                        VerticalPropertyCard(
                            property = item,
                        ) {
                            navController.navigateTo("PropertyDetails/${item.id}")
                        }
                    }
                }
                chunk.getOrNull(1)?.let { secondItem ->
                    Column(modifier = Modifier.weight(1f)) {
                        // Adjust weight for desired ratio
                        VerticalPropertyCard(
                            property = secondItem,
                        ) {
                            navController.navigateTo("PropertyDetails/${secondItem.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListView(
    featuredItems: List<Property>,
    navController: DestinationsNavigator,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        featuredItems.withIndex().forEach { (index, item) ->
            FeatureCardItem(
                modifier = Modifier.fillMaxWidth(),
                property = item,
            ) {
                navController.navigateTo("PropertyDetails/${item.id}")
            }
        }

//        Spacer(modifier = Modifier.height(10.dp))
    }
}
