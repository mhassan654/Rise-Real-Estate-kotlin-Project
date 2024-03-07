package com.saavatech.riserealestate.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun TextWithBlurBg(content: @Composable () -> Unit) {
    Box(
        modifier =
            Modifier
                .background(
                    color = Color.Transparent, // MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(7.dp)
                .clip(RoundedCornerShape(8.dp)) // Clip to ensure that the blur effect doesn't extend beyond the background
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f), // Semi-transparent black color for the blur effect
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(7.dp) // Padding to ensure the blur effect covers the entire background
                .then(Modifier.graphicsLayer(alpha = 0.8f)), // Adjust alpha to control the intensity of blur
    ) {
        content()
//
    }
}
