package com.saavatech.riserealestate.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.saavatech.riserealestate.domain.model.LocationData
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

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

@Composable
fun customAsyncImagePainter(imageUrl: String): Painter {
    return rememberAsyncImagePainter(
        model =
            ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .build(),
    )
}

@SuppressLint("ServiceCast")
fun getCurrentLocation(context: Context): Location? {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        return if (isGpsEnabled) {
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } else {
            // Handle GPS disabled scenario (e.g., prompt user to enable)
            null
        }
    } else {
        // Request location permission if not granted
        return null
    }
}

fun calculateDistance(
    lat1: Double,
    lon1: Double,
    lat2: Double,
    lon2: Double,
): Double {
    val earthRadius = 6371.0 // Earth radius in kilometers (or use miles if preferred)

    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)

    val a =
        kotlin.math.sin(dLat / 2) * kotlin.math.sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            kotlin.math.sin(dLon / 2) * kotlin.math.sin(dLon / 2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    val distance = earthRadius * c

    // Round the distance to the specified number of decimal places
    return Math.round(distance * 10.0.pow(1.0)) / 10.0.pow(1.0)
}

fun getLocationAndDistances(
    context: Context,
    long: Double,
    lati: Double,
): LocationData? {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Handle permission not granted (e.g., request or inform user)
        return null
    }

    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    if (!isGpsEnabled) {
        // Handle GPS disabled scenario (e.g., prompt user to enable)
        return null
    }

    val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER) ?: return null

    val propertyLatitude = lati // London (example)
    val propertyLongitude = long

    val distanceRecord = calculateDistance(location.latitude, location.longitude, propertyLatitude, propertyLongitude)

    return LocationData(
        latitude = location.latitude,
        longitude = location.longitude,
        distance = distanceRecord,
    )
}
