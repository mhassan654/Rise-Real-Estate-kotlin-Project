package com.saavatech.riserealestate.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.inputBg

@Composable
fun TopLocationCard(navigationCallback: (Int) -> Unit) {
    Box(
        modifier =
            Modifier
                .clickable { navigationCallback(1) }
                .width(190.dp)
                .height(280.dp)
                .background(color = inputBg, shape = RoundedCornerShape(22.dp)),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            Image(
                modifier =
                    Modifier
                        .height(210.dp)
                        .width(180.dp).clip(RoundedCornerShape(14.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.image_27),
                contentDescription = null,
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
                modifier =
                    Modifier
                        .fillMaxHeight().fillMaxWidth()
                        .padding(10.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .background(color = GreenOne, shape = RoundedCornerShape(4.dp)),
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        color = Color.White,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center,
                        text = "#2",
                    )
                }
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
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Left,
            )
        }
    }
}
