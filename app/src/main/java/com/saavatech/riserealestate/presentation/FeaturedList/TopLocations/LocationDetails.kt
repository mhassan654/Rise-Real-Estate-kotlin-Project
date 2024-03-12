package com.saavatech.riserealestate.presentation.FeaturedList.TopLocations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.CustomTextField
import com.saavatech.riserealestate.common.HeadingTextComponent
import com.saavatech.riserealestate.common.RoundedCollageImage
import com.saavatech.riserealestate.common.TextDescription
import com.saavatech.riserealestate.common.TitleAndListStyleSwitch
import com.saavatech.riserealestate.common.TransparentTopAppBar
import com.saavatech.riserealestate.presentation.FeaturedList.GridView
import com.saavatech.riserealestate.presentation.FeaturedList.ListView
import com.saavatech.riserealestate.ui.theme.Purple80
import com.saavatech.riserealestate.ui.theme.inputBg

class LocationDetailsViewModel : ViewModel() {
    var switchViewStyle by mutableStateOf(true)
}

@Composable
//@Preview
fun LocationDetails(  navigationCallback: (Int) -> Unit,) {
    var switchViewStyle by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TransparentTopAppBar(
                iconClickAction = {
//                    navController.navigateUp()
                },
                icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                actionIcon = Icons.AutoMirrored.Filled.CompareArrows,
            )

        },
    ) { contentPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize().padding(contentPadding),
        ) {

            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(20.dp),
            ) {
                item {
                    RoundedCollageImage(
                        modifier = Modifier.padding(2.dp).fillMaxWidth(),
                        image1 = painterResource(id = R.drawable.image_28),
                        image2 = painterResource(id = R.drawable.image_29),
                        image3 = painterResource(id = R.drawable.image_27),
                    )
                    HeadingTextComponent("Bali")

                    TextDescription("Our recommended real estates in Kampala")
                    Spacer(modifier = Modifier.height(25.dp))
                    CustomTextField(painterResource(id = R.drawable.search1), "Modern House")

                    Spacer(modifier = Modifier.height(25.dp))
                    TitleAndListStyleSwitch(
                        titleCategory = "estates",
                        counter = 128,
                        content = {
                            Box(
                                modifier =
                                    Modifier
                                        .background(color = inputBg, shape = RoundedCornerShape(23.dp)),
                            ) {
                                Row(
                                    modifier =
                                        Modifier
                                            .padding(8.dp),
                                    horizontalArrangement = Arrangement.End,
                                ) {
                                    Box(
                                        modifier =
                                            Modifier.background(
                                                color = if (switchViewStyle) Color.White else Color.Transparent,
                                                shape = RoundedCornerShape(10.dp),
                                            ).padding(horizontal = 5.dp, vertical = 2.dp),
                                    ) {
                                        IconButton(
                                            onClick = { switchViewStyle = true },
                                            modifier = Modifier.size(15.dp),
                                            content = {
                                                Icon(
                                                    tint = if (switchViewStyle) MaterialTheme.colorScheme.primary else Purple80,
                                                    painter = painterResource(id = R.drawable.grid_view),
                                                    contentDescription = null,
                                                )
                                            },
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(20.dp))

                                    Box(
                                        modifier =
                                            Modifier.background(
                                                color = if (!switchViewStyle) Color.White else Color.Transparent,
                                                shape = RoundedCornerShape(15.dp),
                                            ).padding(horizontal = 4.dp, vertical = 2.dp),
                                    ) {
                                        IconButton(
                                            onClick = { switchViewStyle =false},
                                            modifier = Modifier.size(16.dp),
                                            content = {
                                                Icon(
                                                    tint = if (!switchViewStyle) MaterialTheme.colorScheme.primary else Purple80,
                                                    painter = painterResource(id = R.drawable.baseline_view),
                                                    contentDescription = null,
                                                )
                                            },
                                        )
                                    }
                                }
                            }
                        },
                    )
                    Spacer(modifier = Modifier.height(25.dp))

                    if (switchViewStyle) {
                        GridView(navigationCallback)
                    } else {
                        ListView(navigationCallback)
                    }
                }
            }
        }
    }
}
