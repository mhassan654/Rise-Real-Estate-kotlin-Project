package com.saavatech.riserealestate.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined._360
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Room
import androidx.compose.material.icons.outlined.Room
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.components.ButtonTextComponent
import com.saavatech.riserealestate.components.CustomBlurBg
import com.saavatech.riserealestate.components.GreyButtonTextComponent
import com.saavatech.riserealestate.components.IconWithTextLocation
import com.saavatech.riserealestate.components.RoundedIconButton
import com.saavatech.riserealestate.components.StarRating
import com.saavatech.riserealestate.data.remote.response.AssignFacility
import com.saavatech.riserealestate.data.remote.response.Gallery
import com.saavatech.riserealestate.data.remote.response.Parameter
import com.saavatech.riserealestate.data.remote.response.Property
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.inputBg
import com.saavatech.riserealestate.util.customAsyncImagePainter
import com.saavatech.riserealestate.util.fntSize
import com.saavatech.riserealestate.util.getLocationAndDistances
import com.saavatech.riserealestate.util.rounded25

// @Composable
// @Preview
// fun previewDetailsScreen() {
//    PropertyDetails()
// }

@Composable
fun PropertyDetails(
    property: Property?,
    navController: DestinationsNavigator,
) {
//    val viewModel: PropertyViewModel = hiltViewModel()
//    val propertyState = viewModel.propertyState.value
    val isFabVisible by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val locationData =
        property?.longitude?.let { getLocationAndDistances(context, it.toDouble(), property.latitude.toDouble()) }

    Scaffold(
        floatingActionButton = {
            if (isFabVisible) {
                ButtonTextComponent(value = "Buy Now", clickAction = { /*TODO*/ }, width = 300.dp)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { contentPadding ->

        Box(
            Modifier
                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier =
                    Modifier
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()),
                //                    .fillMaxHeight()
//                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(shape = RoundedCornerShape(30.dp), color = Color.Transparent),
                ) {
                    val painter =
                        rememberAsyncImagePainter(
                            model =
                                ImageRequest.Builder(LocalContext.current)
                                    .data(property?.titleImage)
                                    .build(),
                        )
                    Image(
                        modifier =
                            Modifier
                                .height(520.dp)
//                                .fillMaxWidth()
                                .clip(RoundedCornerShape(30.dp)),
                        contentScale = ContentScale.Crop,
                        painter = painter, // painterResource(id = R.drawable.image_27),
                        contentDescription = null,
                    )

                    if (property != null) {
                        BoxContent(navBackAction = { navController.navigateUp() }, property = property)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        property?.title?.let {
                            Text(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 25.sp,
                                fontWeight = FontWeight(700),
                                text = it,
                            )
                        }
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 22.sp,
                            fontWeight = FontWeight(600),
                            text = "$ ${property?.price}",
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
//                        location text
                        property?.address?.let { IconWithTextLocation(it) }

                        (if (property?.propertyType == "Rent") "per month" else property?.rentduration)?.let {
                            Text(
                                modifier = Modifier.width(100.dp),
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(300),
                                text = it,
                                maxLines = 2,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Row {
                            property?.propertyType?.let {
                                GreyButtonTextComponent(
                                    color =
                                        if (property.propertyType === "sold") {
                                            Color.Red
                                        } else {
                                            MaterialTheme.colorScheme.primary
                                        },
                                    textColor = Color.White,
                                    value = it,
                                    width = 100.dp,
                                    clickAction = { },
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            (if (property?.propertyType == "Sell") "Buy" else null)?.let {
                                GreyButtonTextComponent(
                                    color = inputBg,
                                    textColor = MaterialTheme.colorScheme.primary,
                                    value = it,
                                    width = 100.dp,
                                    clickAction = {},
                                )
                            }
                        }

                        RoundedIconButton(
                            modifier = Modifier.background(color = Color.White),
                            icon = Icons.AutoMirrored.Outlined._360,
                            onClick = { },
                            contentDescription = "Add to favorites",
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 20.dp))

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .background(color = inputBg, shape = RoundedCornerShape(25.dp)),
                    ) {
                        Row(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                                val painter =
                                    rememberAsyncImagePainter(
                                        model =
                                            ImageRequest.Builder(LocalContext.current)
                                                .data(property?.profile)
                                                .build(),
                                    )
                                Image(
                                    modifier =
                                        Modifier
                                            .size(40.dp)
                                            .clip(CircleShape),
                                    contentScale = ContentScale.Crop,
                                    painter = painter,
                                    contentDescription = null,
                                )

                                Column {
                                    property?.let {
                                        Text(
                                            text = it.customerName,
                                        )
                                    }
                                    Text(text = "Real Estate agent")
                                }
                            }

                            Icon(
                                tint = MaterialTheme.colorScheme.primary,
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                contentDescription = null,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        if (property != null) {
                            items(property.parameters) { parameter ->
                                FacilityButton(parameter)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // location header
                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 25.sp,
                        fontWeight = FontWeight(700),
                        text = "Location & Public Facilities",
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    property?.address?.let { userLocation(location = it) }

                    // assigned facilities
                    Spacer(modifier = Modifier.height(20.dp))

                    if (locationData != null) {
                        val currentLatitude = locationData.latitude
                        val currentLongitude = locationData.longitude
                        val distanceToParis = locationData.distance

                        // Use the retrieved data in your Jetpack Compose composables or elsewhere
                        userLocationDistance(distanceToParis)
                        // ...
                    } else {
                        // Handle cases where location data is unavailable (e.g., permission denied, GPS disabled)
                        Text(text = "Error getting location data.")
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        if (property != null) {
                            items(property.assignFacilities) { facility ->
                                PublicFacility(facility)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    val lat = property?.latitude
                    val long = property?.longitude

                    property?.let {
                        if (lat != null) {
                            if (long != null) {
                                PropertyDetailsMap(lat.toDouble(), long.toDouble())
                            }
                        }
                    }


                    // reviews bottom section
                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 25.sp,
                        fontWeight = FontWeight(700),
                        text = "Reviews",
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    reviewsSection()

//                    val location = getCurrentLocation(context = Appl)
                }
            }
        }
    }
}

@Composable
fun userLocationDistance(distance: Double) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(40.dp),
                )
                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(40.dp)),
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Icon(
                modifier = Modifier.padding(14.dp),
                imageVector = Icons.Filled.Room,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                color = MaterialTheme.colorScheme.primary,
                fontSize = fntSize,
                fontWeight = FontWeight(700),
                text = "$distance km",
            )

            Text(
                color = MaterialTheme.colorScheme.primary,
                fontSize = fntSize,
                fontWeight = FontWeight(300),
                text = "from your location",
            )

            Icon(
                modifier =
                    Modifier
                        .padding(14.dp)
                        .size(16.dp),
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
fun BoxContent(
    navBackAction: () -> Unit,
    property: Property,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            Modifier
                .fillMaxHeight()
                .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = { navBackAction.invoke() },
                modifier =
                    Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .padding(horizontal = 2.dp)
                        .padding(vertical = 2.dp)
                        .size(30.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier.size(15.dp),
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    onClick = {},
                    modifier =
                        Modifier
                            .background(color = Color.White, shape = CircleShape)
                            .padding(horizontal = 2.dp)
                            .padding(vertical = 2.dp)
                            .size(30.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ios_share),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {},
                    modifier =
                        Modifier
                            .background(color = GreenOne, shape = CircleShape)
                            .padding(horizontal = 2.dp)
                            .padding(vertical = 2.dp)
                            .size(30.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
        ) {
            Row(verticalAlignment = Alignment.Bottom) {
                CustomBlurBg(content = {
                    StarRating(
                        4.9.toString(),
                        textColor = Color.White,
                    )
                })

                CustomBlurBg(
                    content =
                        {
                            Text(
                                text = property.category.category,
                                modifier =
                                    Modifier
                                        .widthIn(max = 150.dp)
                                        .padding(4.dp),
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.End,
                                color = Color.White,
                            )
                        },
                )
            }

            ImageListWithLimitedDisplay(property.gallery) {}
        }


    }
}

@Composable
fun ImageListWithLimitedDisplay(
    imageList: List<Gallery>, // Replace with your image data source
    onClick: () -> Unit, // Function to handle image gallery opening
) {
    val imageCount = imageList.size
    val displayedImages = imageList.take(3) // Show only the first 3 images

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxHeight(),
    ) {
        displayedImages.forEachIndexed { index, imageUrl ->
            Image(
                painter = customAsyncImagePainter(imageUrl = imageUrl.imageUrl), // Replace with placeholder image resource
                contentDescription = "Image $index",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .border(
                            BorderStroke(2.dp, Color.White),
                            RoundedCornerShape(14.dp),
                        )
                        .clip(RoundedCornerShape(14.dp)),
            )
        }

        if (imageCount > 3) {
            Box(
                modifier =
                    Modifier
                        .weight(1f)
                        .clickable { onClick() }
                        .padding(4.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "+${imageCount - 3}",
                    color = Color.Gray,
                    fontWeight = FontWeight(200),
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 10.sp,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun FacilityButton(parameter: Parameter) {
    Box(
        modifier =
            Modifier.background(
                color = inputBg,
                shape = RoundedCornerShape(rounded25),
            ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val painter =
                rememberAsyncImagePainter(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(parameter.image)
                            .decoderFactory(SvgDecoder.Factory()) // Configure SVG decoder
//                            .placeholder(R.drawable.placeholder_image) // Optional placeholder
//                            .error(R.drawable.error_image) // Optional error drawable
                            .build(),
                )
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painter,
                contentDescription = null,
                tint = GreenOne,
            )

            Text(
                text = parameter.name,
                fontSize = fntSize,
                fontWeight = FontWeight(400),
            )
        }
    }
}

@Composable
fun PublicFacility(facility: AssignFacility) {
    Box(
        modifier =
            Modifier.background(
                color = inputBg,
                shape = RoundedCornerShape(rounded25),
            ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var painter =
                rememberAsyncImagePainter(
                    model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(facility.image)
                            .decoderFactory(SvgDecoder.Factory()) // Configure SVG decoder
//                            .placeholder(R.drawable.placeholder_image) // Optional placeholder
//                            .error(R.drawable.error_image) // Optional error drawable
                            .build(),
                )
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painter,
                contentDescription = null,
                tint = GreenOne,
            )

            Text(
                text = facility.name,
                fontSize = fntSize,
                fontWeight = FontWeight(400),
            )
        }
    }
}

@Composable
fun userLocation(location: String) {
    Box(
        modifier = Modifier.padding(18.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Box(
                modifier =
                    Modifier.background(
                        color = inputBg,
                        shape = CircleShape,
                    ),
            ) {
                Icon(
                    modifier = Modifier.padding(14.dp),
                    imageVector = Icons.Outlined.Room,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            Text(
                color = MaterialTheme.colorScheme.primary,
                fontSize = fntSize,
                text = location,
            )
        }
    }
}

// @Preview
@Composable
fun PropertyDetailsMap(
    lat: Double,
    long: Double,
) {
    val city = LatLng(lat, long)
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(city, 10f)
        }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(shape = RoundedCornerShape(rounded25), color = Color.Blue),
    ) {
        GoogleMap(
            modifier =
                Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = inputBg, shape = RoundedCornerShape(25.dp)),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = city),
                title = "city",
                snippet = "city",
            )
        }

        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray).align(Alignment.BottomCenter),
        ) {
            Text(
                modifier = Modifier.align(Alignment.BottomCenter).padding(vertical = 6.dp),
                text = "View On Full Map",
            )
        }
    }
}

@Composable
fun ReviewItem() {
    Box(
        modifier =
            Modifier.background(
                color = inputBg,
                shape = RoundedCornerShape(20.dp),
            ),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            Image(
                modifier =
                    Modifier
                        .size(45.dp)
                        .border(
                            BorderStroke(2.8.dp, Color.White),
                            shape = CircleShape,
                        )
                        .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = null,
            )

            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        text = "Hassan Saava",
                    )

                    Text(text = "Saava")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text =
                        "lorem ipsum jsdvsjbjsdkjvsdkjvkjsdvkdsvkssdv" +
                            "skjdjksvjsjkvdsksg,jdfjkkhkdfgjkdfgjkjfkf" +
                            "sdfgksdjdhfjdsfkgfdkskdfg" +
                            "sdjskdgjksjdgjkssrugfjdhgsjgfdjksgfd" +
                            "hsbdcjsdhvbshdbh",
                    fontSize = 10.sp,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    fontSize = 7.sp,
                    text = "12 days ago",
                )
            }
        }
    }
}

@Composable
@Preview
fun previewReviewComponent() {
    ReviewItem()
}

@Composable
@Preview
fun reviewsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(2) {
                ReviewItem()
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier.background(
                    color = inputBg,
                    shape = RoundedCornerShape(16.dp),
                )
                    .fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                text = "View All reviews",
            )
        }
    }
}
