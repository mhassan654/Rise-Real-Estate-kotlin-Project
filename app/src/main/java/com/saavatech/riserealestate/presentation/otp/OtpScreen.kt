package com.saavatech.riserealestate.presentation.otp

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.Destinations
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.AppBar
import com.saavatech.riserealestate.common.ButtonTextComponent
import com.saavatech.riserealestate.common.CustomOutlinedPasswordTextField
import com.saavatech.riserealestate.common.CustomOutlinedTextField
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtpScreen( navController: DestinationsNavigator){
    Scaffold(
        topBar = {
            AppBar(
                title = null,
                actionIcon = null,
                icon= Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                iconClickAction = { navController.navigateUp()}
            )
        },
    ) {innerPadding->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .padding(vertical = 30.dp)
                    ) {

                        Text(
                            text = stringResource(id = R.string.enter_the_code),
                            fontSize = 25.sp,
                            fontWeight = FontWeight(400),
                            color = TextColorOne
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = stringResource(id = R.string.code),
                            fontSize = 25.sp,
                            fontWeight = FontWeight(700),
                            color = TextColorBold
                        )
                    }

                    Text(
                        text = "Enter the 4 digit code that we just sent to hassansaava@gmail.com",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = TextColorOne
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 60.dp)
                ) {
                    var otpValue by remember {
                        mutableStateOf("")
                    }


//                    BasicTextField(
//                        value = otpValue,
//                        onValueChange = {
//                            if (it.length <= 4){
//                                otpValue =it
//                            }
//                        },
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.NumberPassword
//                        ),
//                        decorationBox = {
//                            Row(horizontalArrangement = Arrangement.Center) {
//                                repeat(4){index->
//                                    val char = when{
//                                        index > otpValue.length ->""
//                                        else -> otpValue[index].toString()
//                                    }
//
//                                    val isFocused = otpValue.length == index
//
//                                    Text(
//                                        modifier = Modifier
//                                            .width(50.dp)
//                                            .border(
//                                                if (isFocused) 2.dp
//                                                else 1.dp,
//
//                                                if (isFocused)
//                                                colorResource(id = R.color.socialButtonBgColor)
//                                                        else
//                                                    colorResource(id = R.color.socialButtonBgColor),
//                                                RoundedCornerShape(8.dp)
//                                            ),
//                                        text = char,
//                                        style = MaterialTheme.typography.headlineMedium,
//                                        color =  colorResource(id = R.color.socialButtonBgColor),
//                                        textAlign = TextAlign.Center
//                                    )
//
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                }
//
//                            }
//                        }
//                    )

                    BasicTextField(
                        value = otpValue,
                        onValueChange = { newValue ->
                            if (newValue.length <= 4) {
                                otpValue = newValue
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.NumberPassword
                        ),
                        decorationBox = {
                            Row(horizontalArrangement = Arrangement.Center) {
                                repeat(4) { index ->
                                    val char = if (index < otpValue.length) otpValue[index].toString() else ""

                                    val isFocused = otpValue.length == index

                                    Text(
                                        modifier = Modifier
                                            .width(60.dp)
                                            .height(60.dp)
                                            .background(colorResource(id = R.color.socialButtonBgColor))
                                            .border(
                                                width = if (isFocused) 2.dp else 1.dp,
                                                color = if (isFocused) colorResource(id = R.color.socialButtonBgColor) else colorResource(
                                                    id = R.color.socialButtonBgColor
                                                ),
                                                shape = RoundedCornerShape(13.dp)
                                            ),
                                        text = char,
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = colorResource(id = R.color.socialButtonBgColor),
                                        textAlign = TextAlign.Center
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                        }
                    )

                }

                Row(
                    modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start) {
                    TextButton(
                        onClick = {

                            navController.navigateTo(Destinations.Register.route)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = TextColorOne,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight(500),
                                    )
                                ) {
                                    append(stringResource(id = R.string.terms_of_service))
                                }
                            },
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            color = TextColorBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier= Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    ButtonTextComponent(value = "Register", clickAction = {  }, 280.dp)
                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OtpPreview(){
    val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)
    OtpScreen(destinationsNavigator)
}