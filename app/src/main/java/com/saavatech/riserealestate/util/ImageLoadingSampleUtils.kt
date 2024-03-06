package com.saavatech.riserealestate.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

private val rangeForRandom = (0..3)

fun randomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String {
    return "https://picsum.photos/seed/$seed/$width/$height"
}

/**
 * Remember a URL generate by [randomSampleImageUrl].
 */
@Composable
fun rememberRandomSampleImageUrl(
    seed: Int = rangeForRandom.random(),
    width: Int = 300,
    height: Int = width,
): String = remember { randomSampleImageUrl(seed, width, height) }

public val randomSizedPhotos =
    listOf(
        randomSampleImageUrl(width = 1600, height = 900),
        randomSampleImageUrl(width = 900, height = 1000),
        randomSampleImageUrl(width = 500, height = 500),
    )
