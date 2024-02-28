package com.saavatech.riserealestate.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.AppBar
import com.saavatech.riserealestate.common.CategoryButtonTextComponent
import com.saavatech.riserealestate.common.CustomOutlinedTextField
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.primaryBackground1

@Composable
fun Home() {
    Scaffold(
        topBar = {
            AppBar(
                title = null,
                actionIcon = null,
                icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                iconClickAction = { },
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp),
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
                    Row {
                        repeat(6) {
                            CategoryButtonTextComponent("All", {})
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    LazyRow{
                        items(4){index->
                            JetpackCard(
                                title = "sample",
                                subTitle = "sampledfhdfghjgf",
                                backgroundResId = painterResource(id = R.drawable.image_25),
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                        }
                    }


                }
            }
        }
//
    }
}

@Composable
@Preview(showBackground = true)
fun HomePreview() {
    Home()
}

@Composable
@Preview(showBackground = true)
fun JetpackCardP() {
    JetpackCard(
        title = "sample",
        subTitle = "sampledfhdfghjgf",
        backgroundResId = painterResource(id = R.drawable.image_25),
    )
}

@Composable
fun JetpackCard(
    modifier: Modifier = Modifier,
    backgroundResId: Painter,
    title: String,
    subTitle: String,
) {
    Surface(
        modifier =
            modifier
//                .padding(0.dp)
                .width(260.dp).height(180.dp),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp, bottomEnd = 28.dp, bottomStart = 0.dp),
//        elevation = 8.dp
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
//                modifier = Modifier.fillMaxSize(),
            ) {
                TextOverlayTitle(text = title)
                Spacer(modifier = Modifier.height(16.dp))

                TextOverlaySubTitle(text = subTitle)
            }

            Spacer(modifier = Modifier.height(14.dp))
            TextOverlaySubTitle(text = subTitle)

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
                                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 25.dp, bottomEnd = 0.dp, bottomStart = 0.dp),
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
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
        )
    }
}

@Composable
private fun TextOverlaySubTitle(text: String) {
    Column(
        modifier =
            Modifier
//                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
        )
    }
}
