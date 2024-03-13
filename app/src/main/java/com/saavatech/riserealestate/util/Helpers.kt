package com.saavatech.riserealestate.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun LoadSvgImage(svgImageUrl: String) {
    val context = LocalContext.current
    val painter =
        rememberAsyncImagePainter(
            model =
                ImageRequest.Builder(context)
                    .data(svgImageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
        )

    Image(
        painter = painter,
        contentDescription = "SVG Image", // Provide a descriptive contentDescription
        modifier = Modifier.size(100.dp), // Adjust size as needed
    )
}
