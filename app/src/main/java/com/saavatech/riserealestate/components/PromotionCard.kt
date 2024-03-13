package com.saavatech.riserealestate.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.ui.theme.primaryBackground1

@Composable
fun PromotionCard(
    backgroundResId: Painter,
    title: String,
    subTitle: String,
    width: Dp,
    onClickAction: () -> Unit?,
) {
    Surface(
        modifier =
            Modifier
                .width(width)
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
                contentScale = ContentScale.Crop,
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
                        onClick = { onClickAction.invoke() },
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
