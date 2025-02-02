package com.saavatech.riserealestate.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Room
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.ui.theme.ButtonBgOne
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.Purple80
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.inputBg
import com.saavatech.riserealestate.ui.theme.primaryBackground1

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier =
            Modifier
                .fillMaxWidth()
                .heightIn(),
        style =
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
        color = TextColorOne,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier =
            Modifier
                .fillMaxWidth()
                .heightIn(min = 20.dp),
        style =
            TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
            ),
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun TextDescription(value: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = value,
        fontSize = 15.sp,
        fontWeight = FontWeight(300),
        color = MaterialTheme.colorScheme.primary,
    )
}

@Composable
fun IconWithTextLocation(location: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.padding(6.dp)) {
            Icon(
                tint = primaryBackground1,
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Rounded.Room,
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                overflow = TextOverflow.Ellipsis,
                text = location,
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = TextColorOne,
                maxLines = 3,
            )
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    painterResource: Painter,
    lableValue: String,
    textValue: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    OutlinedTextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(1.dp)),
        label = { Text(text = lableValue) },
        value = textValue,
        shape = RoundedCornerShape(8.dp),
        placeholder = placeholder,
        colors =
            OutlinedTextFieldDefaults.colors(
                cursorColor = ButtonBgOne,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = inputBg,
//                errorTextColor = MaterialTheme.colorScheme.error,
            ),
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        isError = isError,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource,
                contentDescription = "profile icon",
            )
        },
    )
}

@Composable
fun CustomTextField(
    painterResource: Painter,
    lableValue: String,
) {
    val text =
        remember {
            mutableStateOf("")
        }

    TextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(1.dp)),
        label = { Text(text = lableValue) },
        value = text.value,
        shape = RoundedCornerShape(18.dp),
        colors =
            TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent, // removes unfocused bottom bordr colore
                focusedIndicatorColor = Color.Transparent, // removes focused bottom bordr colore
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = inputBg,
            ),
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            text.value = it
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource,
                contentDescription = "profile icon",
            )
        },
    )
}

@Composable
fun CustomOutlinedPasswordTextField(
    passwordState: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
) {
    val passwordVisibility =
        remember {
            mutableStateOf(false)
        }

    OutlinedTextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(1.dp)),
        label = { Text(text = "Password") },
        value = passwordState,
        shape = RoundedCornerShape(8.dp),
        colors =
            OutlinedTextFieldDefaults.colors(
                cursorColor = ButtonBgOne,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent,
                unfocusedContainerColor = inputBg,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = onValueChange,
        isError = isError,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.lock),
                contentDescription = "profile icon",
            )
        },
        trailingIcon = {
            val iconImage =
                if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

            val description =
                if (passwordVisible) {
                    "Hide Password"
                } else {
                    "Show password"
                }

            IconButton(onClick = {
                onPasswordVisibilityChange(!passwordVisible)
            }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation =
            if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
    )
}

@Composable
fun ButtonTextComponent(
    value: String,
    clickAction: () -> Unit,
    width: Dp,
) {
    TextButton(
        modifier =
            Modifier
                .width(width)
                .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(GreenOne),
        shape = RoundedCornerShape(15),
        onClick = { clickAction.invoke() },
    ) {
        Text(text = value)
    }
}

@Composable
fun GreyButtonTextComponent(
    value: String,
    clickAction: () -> Unit,
    width: Dp,
    color: Color,
    textColor: Color,
) {
    TextButton(
        modifier =
            Modifier
                .width(width)
                .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(20.dp),
        onClick = { clickAction.invoke() },
    ) {
        Text(text = value, color = textColor)
    }
}

@Composable
fun CategoryButtonTextComponent(
    value: String,
    clickAction: () -> Unit,
//    width: Dp,
) {
    TextButton(
        modifier =
            Modifier
//                .width(width)
                .heightIn(40.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(primaryBackground1),
        shape = RoundedCornerShape(50),
        onClick = { clickAction.invoke() },
    ) {
        Text(text = value)
    }
}

@Composable
fun ButtonIconComponent(value: String) {
    IconButton(
        modifier =
            Modifier
                .width(150.dp)
                .heightIn(40.dp),
        //        contentPadding = PaddingValues(),
//        colors = ButtonDefaults.buttonColors(GreenOne),
//        shape = RoundedCornerShape(15),
        onClick = { },
    ) {
        Text(text = value)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String?,
    icon: ImageVector,
    actionIcon: ImageVector?,
    iconClickAction: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 10.dp).background(color = Color.Transparent),
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
//                titleContentColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        navigationIcon = {
            RoundedIconButton(
                modifier = Modifier.padding(2.dp),
                icon = icon,
                onClick = { iconClickAction.invoke() },
                contentDescription = "Add to favorites",
            )
        },
        actions = {
            if (actionIcon != null) {
                RoundedIconButton(
                    icon = actionIcon,
                    onClick = { iconClickAction.invoke() },
                    contentDescription = "Share to others",
                )
            }
        },
        title = {
            if (title != null) {
                Text(title)
            }
        },
    )
}

@Composable
fun RoundedIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    contentDescription: String? = null,
) {
    IconButton(
        onClick = onClick,
        modifier =
            Modifier
                .background(colorResource(id = R.color.socialButtonBgColor), shape = CircleShape)
                .padding(horizontal = 2.dp)
                .padding(vertical = 2.dp)
                .size(50.dp),
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp),
        )
    }
//    }
}

@Composable
fun RoundedIconTextButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    contentDescription: String? = null,
    text: String,
) {
    Row {
        TextButton(
            modifier =
                Modifier
                    .widthIn(278.dp)
                    .height(63.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(GreenOne),
            shape = RoundedCornerShape(15),
            onClick = { onClick.invoke() },
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = icon,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = text, fontSize = 16.sp, fontWeight = FontWeight(600))
            }
        }
    }

//    }
}

@Composable
fun InnerCurvedBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Blue,
    cornerRadius: Float = 20f,
    curveAngle: Float = 45f,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val boxWidth = size.width
            val boxHeight = size.height
            val innerWidth = boxWidth * 0.5f
            val innerHeight = boxHeight * 0.5f
            val radius = CornerRadius(cornerRadius)
            drawRoundRect(color = backgroundColor, cornerRadius = radius)

            rotate(curveAngle) {
                drawRoundRect(
                    color = Color.White,
                    cornerRadius = radius,
                    topLeft = Offset(-innerWidth / 2, -innerHeight / 2),
                    size = Size(innerWidth, innerHeight),
                    style = Stroke(2.dp.toPx()),
                )
            }
        }
        content()
    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HorizontalDivider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
            thickness = 1.dp,
            color = colorResource(id = R.color.strokeColor),
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "OR",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = colorResource(id = R.color.strokeTextColor),
        )

        HorizontalDivider(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
            thickness = 1.dp,
            color = colorResource(id = R.color.strokeColor),
        )
    }
}

@Composable
fun SocialButton(
    icon: Painter,
    onClick: () -> Unit,
) {
    TextButton(
        modifier =
            Modifier
                .width(158.5.dp)
                .height(70.dp),
        contentPadding = PaddingValues(),
        colors =
            ButtonDefaults.buttonColors(
                colorResource(id = R.color.socialButtonBgColor),
            ),
        shape = RoundedCornerShape(40),
        onClick = { },
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = icon,
            contentDescription = null,
            tint = TextColorOne,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsPreview() {
    Column(modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(6.dp))
        CustomTextField(painterResource(id = R.drawable.profile_image), "text field")
        Spacer(modifier = Modifier.height(6.dp))
        ButtonTextComponent(value = "button with text", clickAction = {}, 150.dp)
        Spacer(modifier = Modifier.height(6.dp))

        RoundedIconTextButton(
            icon = Icons.Default.MailOutline,
            onClick = { },
            contentDescription = "Add to favorites",
            text = "Sample text icon button",
        )
        Spacer(modifier = Modifier.height(6.dp))

        AppBar(
            "Details",
            Icons.AutoMirrored.Filled.ArrowBack,
            Icons.Filled.Share,
            iconClickAction = {
//                navController?.navigateUp()
            },
        )
        Spacer(modifier = Modifier.height(6.dp))
        DividerTextComponent()

        SocialButton(
            icon = painterResource(id = R.drawable.google_48),
            onClick = {},
        )
    }
}

@Composable
@Preview
fun InnerCurvedBoxDemo() {
    InnerCurvedBox(Modifier.fillMaxSize(), backgroundColor = Color.Blue) {
        // Add your content here
    }
}

@Composable
fun sectionTitles(
    title: String,
    title2: String?,
    onClick: () -> Unit?,
) {
    Spacer(modifier = Modifier.height(25.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Left,
        )

        if (title2 != null) {
            Text(
                modifier = Modifier.clickable { onClick.invoke() },
                text = title2,
                style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                fontSize = 14.sp,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Left,
            )
        }
    }
    Spacer(modifier = Modifier.height(25.dp))
}

// location widget
@Composable
fun IconWithLocation(location: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.primary,
            imageVector = Icons.Rounded.Room,
            contentDescription = null,
            modifier = Modifier.size(13.dp),
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
            fontSize = 15.sp,
            text = location,
        )
    }
}

// rating widget
@Composable
fun StarRating(
    rate: String,
    textColor: Color?,
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            tint = Color.Yellow,
            imageVector = Icons.Rounded.StarRate,
            contentDescription = null,
            modifier = Modifier.size(15.dp),
        )

        Spacer(modifier = Modifier.width(5.dp))

        textColor?.let {
            Text(
                style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                fontSize = 15.sp,
                color = it,
                fontWeight = FontWeight(500),
                text = rate,
            )
        }
    }
}

@Composable
fun CollageImage(
    modifier: Modifier = Modifier,
    image1: Painter,
    image2: Painter,
    image3: Painter,
) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier =
                modifier
                    .fillMaxWidth(),
        ) {
            Image(
                contentScale = ContentScale.Fit,
                modifier =
                    Modifier
                        .height(224.dp)
                        .width(220.dp).clip(RoundedCornerShape(10.dp)),
                contentDescription = "Image 3",
                painter = image3,
            )
            Column(
                verticalArrangement = Arrangement.Top,
                modifier =
                Modifier,
            ) {
                Image(
                    contentScale = ContentScale.Fit,
                    modifier =
                        Modifier.width(133.dp).height(137.dp)
                            .clip(RoundedCornerShape(10.dp)),
                    contentDescription = "Image 1",
                    painter = image1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Image(
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.width(133.dp).height(137.dp).clip(RoundedCornerShape(10.dp)),
                    contentDescription = "Image 2",
                    painter = image2,
                )
            }
        }
    }
}

@Composable
fun CustomGridView(
    content: @Composable () -> Unit,
    repeat: Int,
) {
    repeat(repeat) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(2) {
                content()
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ListSwitch(defaultSwitchViewStyle: Boolean) {
    var switchViewStyle by remember { mutableStateOf(defaultSwitchViewStyle) }
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
                    onClick = {
                        switchViewStyle = true
                    },
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
                    onClick = { !switchViewStyle },
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
}

@Composable
fun RoundedCollageImage(
    modifier: Modifier = Modifier,
    image1: Painter,
    image2: Painter,
    image3: Painter,
) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier =
                modifier
                    .fillMaxWidth(),
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .height(375.dp)
                        .width(250.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 35.dp,
                                topEnd = 10.dp,
                                bottomStart = 35.dp,
                                bottomEnd = 10.dp,
                            ),
                        ),
                contentDescription = "Image 3",
                painter = image3,
            )
            Column(
                verticalArrangement = Arrangement.Top,
                modifier =
                    Modifier.height(375.dp),
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier.width(130.dp).height(270.dp)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 35.dp,
                                    topStart = 12.dp,
                                    bottomStart = 12.dp,
                                    bottomEnd = 12.dp,
                                ),
                            ),
                    contentDescription = "Image 1",
                    painter = image1,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier.width(130.dp).height(105.dp)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 12.dp,
                                    topStart = 12.dp,
                                    bottomStart = 12.dp,
                                    bottomEnd = 35.dp,
                                ),
                            ),
                    contentDescription = "Image 2",
                    painter = image2,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCollegeRounded() {
    RoundedCollageImage(
        modifier = Modifier,
        image1 = painterResource(id = R.drawable.image_28),
        image2 = painterResource(id = R.drawable.image_29),
        image3 = painterResource(id = R.drawable.image_27),
    )
}
