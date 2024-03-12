package com.saavatech.riserealestate.presentation

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.AltRoute
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.ForwardToInbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.components.AppBar
import com.saavatech.riserealestate.components.PromotionCard
import com.saavatech.riserealestate.ui.theme.GreenTwo
import com.saavatech.riserealestate.ui.theme.lightGreen

@Preview(showBackground = true)
@Composable
fun PromotionScreenPreview() {
    PromotionScreen(navController = null)
}

@Composable
fun PromotionScreen(navController: DestinationsNavigator?) {
    Scaffold(
        topBar =
            {
                AppBar(
                    iconClickAction = {
                        navController?.navigateUp()
                    },
                    title = null,
                    icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    actionIcon = Icons.AutoMirrored.Rounded.ForwardToInbox,
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 20.dp),
            ) {
                // promotion card
                PromotionCard(
                    title = "sample",
                    subTitle = "All discount up to 60%",
                    backgroundResId = painterResource(id = R.drawable.image_25),
                    width = 350.dp,
                    onClickAction = {},
                )

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Limited time halloween sale is coming back",
                        fontSize = 25.sp,
                        fontWeight = FontWeight(400),
                        color = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // date section
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Icon(
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(13.dp),
                            painter = painterResource(id = R.drawable.calendar_today),
                            contentDescription = null,
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "October 27, 2022",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }

                    // coupon section
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .background(color = lightGreen, shape = RoundedCornerShape(25.dp)),
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier =
                                    Modifier
                                        .background(color = GreenTwo, shape = RoundedCornerShape(20.dp)),
                            ) {
                                Icon(
                                    modifier =
                                        Modifier
                                            .padding(19.dp),
//                                            .background(color = GreenTwo, shape = RoundedCornerShape(25.dp)),
                                    tint = Color.White,
                                    imageVector = Icons.AutoMirrored.Outlined.AltRoute,
                                    contentDescription = null,
                                )
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            Column {
                                Text(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(700),
                                    color = MaterialTheme.colorScheme.primary,
                                    text = "HLWN40",
                                )

                                Text(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight(300),
                                    color = MaterialTheme.colorScheme.primary,
                                    text = "Use this coupon to get 40% off on your transaction",
                                )
//
                            }
//
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(300),
                        color = MaterialTheme.colorScheme.primary,
                        text =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip.\n" +
                                "\n" +
                                "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores",
                    )
                }
            }
        }
    }
}
