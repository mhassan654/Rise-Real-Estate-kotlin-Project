package com.saavatech.riserealestate.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined._360
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.ButtonTextComponent
import com.saavatech.riserealestate.common.CustomBlurBg
import com.saavatech.riserealestate.common.GreyButtonTextComponent
import com.saavatech.riserealestate.common.IconWithTextLocation
import com.saavatech.riserealestate.common.RoundedIconButton
import com.saavatech.riserealestate.common.StarRating
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.inputBg

@Composable
@Preview
fun PropertyDetails() {
    Scaffold(
        floatingActionButton = {
            ButtonTextComponent(value = "Buy Now", clickAction = { /*TODO*/ }, width = 300.dp)
        },
    ) { contentPadding ->

        Box(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(8.dp),
//                    .fillMaxHeight()
//                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(shape = RoundedCornerShape(30.dp), color = Color.Transparent),
                ) {
                    Image(
                        modifier =
                            Modifier
                                .height(520.dp)
//                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp)),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.image_27),
                        contentDescription = null,
                    )

                    BoxContent()
                }

                Spacer(modifier = Modifier.height(30.dp))

                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(700),
                            text = "Hotel Triangle",
                        )
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(600),
                            text = "$ 220",
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        IconWithTextLocation("Jarkat Kampala")
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(300),
                            text = "per month",
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row {
                            GreyButtonTextComponent(
                                color = MaterialTheme.colorScheme.primary,
                                textColor = Color.White,
                                value = "Rent",
                                width = 100.dp,
                                clickAction = {},
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            GreyButtonTextComponent(
                                color = inputBg,
                                textColor = MaterialTheme.colorScheme.primary,
                                value = "Buy",
                                width = 100.dp,
                                clickAction = {},
                            )
                        }

                        RoundedIconButton(
                            modifier = Modifier.background(color = Color.White),
                            icon = Icons.AutoMirrored.Outlined._360,
                            onClick = { },
                            contentDescription = "Add to favorites",
                        )
//
                    }

                    Divider(modifier = Modifier.padding(vertical = 20.dp))

                    Box(
                        modifier =Modifier.fillMaxWidth().background(color = inputBg, shape = RoundedCornerShape(25.dp)),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                                Image(
                                    modifier =
                                        Modifier
                                            .size(40.dp)
                                            .clip(CircleShape),
                                    contentScale = ContentScale.Crop,
                                    painter = painterResource(id = R.drawable.profile_image),
                                    contentDescription = null,
                                )

                                Column {
                                    Text(
                                        text = "Hassan Saava")
                                    Text(text = "Real Estate agent")
                                }
                            }

                            Icon(
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                contentDescription = null,
                            )
                        }
                    }

                    Box(
                        modifier =Modifier.fillMaxWidth().background(color = inputBg, shape = RoundedCornerShape(25.dp)),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                                Image(
                                    modifier =
                                    Modifier
                                        .size(40.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop,
                                    painter = painterResource(id = R.drawable.profile_image),
                                    contentDescription = null,
                                )

                                Column {
                                    Text(
                                        text = "Hassan Saava")
                                    Text(text = "Real Estate agent")
                                }
                            }

                            Icon(
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BoxContent() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            Modifier
                .fillMaxHeight()
//                                .fillMaxWidth()
                .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = {},
                modifier =
                    Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .padding(horizontal = 2.dp)
                        .padding(vertical = 2.dp)
                        .size(30.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    onClick = {},
                    modifier =
                        Modifier
                            .background(color = Color.White, shape = CircleShape)
                            .padding(horizontal = 2.dp)
                            .padding(vertical = 2.dp)
                            .size(30.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ios_share),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {},
                    modifier =
                        Modifier
                            .background(color = GreenOne, shape = CircleShape)
                            .padding(horizontal = 2.dp)
                            .padding(vertical = 2.dp)
                            .size(30.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                CustomBlurBg(content = {
                    StarRating(
                        4.9.toString(),
                        textColor = Color.White,
                    )
                })

                CustomBlurBg(
                    content =
                        {
                            Text(
                                text = "Apartment",
                                modifier =
                                    Modifier
                                        .widthIn(max = 150.dp)
                                        .padding(4.dp),
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.End,
                                color = Color.White,
                            )
                        },
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End,
            ) {
                repeat(3) {
                    Image(
                        modifier =
                            Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .border(
                                    BorderStroke(2.dp, Color.White),
                                    RoundedCornerShape(14.dp),
                                )
                                .clip(RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.image_29),
                        contentDescription = null,
                    )
                }
//                                Spacer(modifier = Modifier.height(4.dp))
            }
        }

//        Spacer(modifier = Modifier.height(4.dp))
    }
}
