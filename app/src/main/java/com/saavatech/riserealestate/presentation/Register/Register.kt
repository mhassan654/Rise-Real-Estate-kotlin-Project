package com.saavatech.riserealestate.presentation.Register

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
import com.saavatech.riserealestate.common.DividerTextComponent
import com.saavatech.riserealestate.common.SocialButton
import com.saavatech.riserealestate.presentation.login.LoginScreen
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen( navController: DestinationsNavigator){
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
                                text = stringResource(id = R.string.create_your_account),
                                fontSize = 25.sp,
                                fontWeight = FontWeight(400),
                                color = TextColorOne
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = stringResource(id = R.string.account),
                                fontSize = 25.sp,
                                fontWeight = FontWeight(700),
                                color = TextColorBold
                            )
                        }

                        Text(
                            text = "Welcome back, please sign up to continue to your account",
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = TextColorOne
                        )
                    }


                    Spacer(modifier = Modifier.height(35.dp))
                    CustomOutlinedTextField(painterResource(id = R.drawable.profile), "Full name")
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomOutlinedTextField(painterResource(id = R.drawable.email), "Email")
                    Spacer(modifier = Modifier.height(6.dp))
                    // password field
                    CustomOutlinedPasswordTextField("Password")

                    Row(
                        modifier=Modifier.fillMaxWidth(),
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
                        modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        ButtonTextComponent(value = "Register", clickAction = {  }, 280.dp)
                    }


                }

            }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)
    RegisterScreen(destinationsNavigator)
}