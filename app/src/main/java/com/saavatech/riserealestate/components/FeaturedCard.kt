package com.saavatech.riserealestate.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg

@Composable
fun FeatureCardItem(
    modifier: Modifier,
    property: Property,
    navigationCallback: () -> Unit,
) {
    Box(
        modifier
            .clickable { navigationCallback.invoke() }
            .height(190.dp)
            .background(color = inputBg, shape = RoundedCornerShape(20.dp)),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(8.dp),
        ) {
            Column {
                Box {
                    val painter =
                        rememberAsyncImagePainter(
                            model =
                                ImageRequest.Builder(LocalContext.current)
                                    .data(property.titleImage)
                                    .build(),
                        )

                    Image(
                        modifier =
                            Modifier
                                .height(180.dp)
                                .width(180.dp).clip(RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop,
                        painter = painter,
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

                        TextWithBlurBg(
                            content =
                                { Text(text = property.state, color = Color.White, fontWeight = FontWeight(300)) },
                        )
                    }
                }
            }

            Column(
                modifier =
                    Modifier
                        .padding(2.dp)
                        .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                ) {
                    Text(
//                    modifier = Modifier.height(40.dp),
                        text = property.title,
                        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                        fontSize = 19.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Left,
                    )

                    StarRating(4.9.toString(), textColor = null)
                    IconWithLocation(property.city)
                }

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
                                append("$ ${property.price}")
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
