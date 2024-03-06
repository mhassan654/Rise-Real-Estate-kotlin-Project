package com.saavatech.riserealestate.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg

@Composable
fun FeatureCardItem(modifier: Modifier?) {
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
