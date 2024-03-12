package com.saavatech.riserealestate.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Room
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
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg
import com.saavatech.riserealestate.ui.theme.primaryBackground1

//@Preview(showBackground = true)
@Composable
fun VerticalPropertyCard(navigationCallback: (Int) -> Unit,) {
    Box(
        modifier =
            Modifier
                .width(190.dp)
                .height(300.dp)
                .background(color = inputBg, shape = RoundedCornerShape(22.dp)),
    ) {
        Box(
            Modifier
                .clickable { navigationCallback(1) }
                .fillMaxSize()
                .padding(8.dp),
        ) {
            Image(
                modifier = Modifier
                .height(180.dp)
                    .width(180.dp).clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.image_27),
                contentDescription = null,
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
                modifier =
                    Modifier
                        .fillMaxHeight().fillMaxWidth()
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
                    {
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
                                .padding(4.dp),
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.End,
                            color = Color.White,
                        )
                    })



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

            Row(verticalAlignment = Alignment.CenterVertically) {
                StarRating(4.9.toString(),textColor=null)
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
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = TextColorOne,
                    )
                }
            }

        }
    }
}
