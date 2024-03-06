package com.saavatech.riserealestate.presentation.FeaturedEstates

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.AppBar
import com.saavatech.riserealestate.common.CustomTextField
import com.saavatech.riserealestate.common.FeatureCardItem
import com.saavatech.riserealestate.ui.theme.Purple80
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg
import com.saavatech.riserealestate.util.randomSizedPhotos

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
            Column(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(15.dp),
            ) {
                LazyStaggerGridSnippetFixed()

                Spacer(modifier = Modifier.height(15.dp))

                Column {
                    Text(
                        text = "Featured Estates",
                        fontSize = 25.sp,
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Text(
                        text = "Our recommended real estates exclusive for you",
                        fontSize = 13.sp,
                        fontWeight = FontWeight(300),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                CustomTextField(painterResource(id = R.drawable.search1), "Search House, Apartment, etc")

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
                                    append("70")
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
                                    .padding(10.dp),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                tint = Purple80,
                                painter = painterResource(id = R.drawable.grid_view),
                                contentDescription = null,
                            )
                            Spacer(modifier = Modifier.width(20.dp))

                            Icon(
                                modifier = Modifier.size(16.dp),
                                tint = Purple80,
                                painter = painterResource(id = R.drawable.baseline_view),
                                contentDescription = null,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))

                LazyColumn {
                    items(4) {
                        FeatureCardItem(
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LazyStaggerGridSnippetFixed() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(randomSizedPhotos) { photo ->
                AsyncImage(
                    model = photo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                )
            }
        },
    )
}
