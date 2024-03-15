package com.saavatech.riserealestate.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.components.AppBar
import com.saavatech.riserealestate.components.ButtonTextComponent
import com.saavatech.riserealestate.components.CustomOutlinedPasswordTextField
import com.saavatech.riserealestate.components.CustomOutlinedTextField
import com.saavatech.riserealestate.components.DividerTextComponent
import com.saavatech.riserealestate.components.SocialButton
import com.saavatech.riserealestate.navigation.Destinations
import com.saavatech.riserealestate.presentation.viewModel.AuthViewModel
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            AppBar(
                title = null,
                actionIcon = null,
                icon = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                iconClickAction = { navController.navigateUp() },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.undraw_city_life_gnpr_1), // painterResource(id = R.drawable.login3),
                    contentDescription = null,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                    contentScale = ContentScale.Fit,
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                    modifier =
                        Modifier
                            .padding(vertical = 30.dp, horizontal = 10.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.lets),
                        fontSize = 25.sp,
                        fontWeight = FontWeight(400),
                        color = TextColorOne,
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = stringResource(id = R.string.sign_in),
                        fontSize = 25.sp,
                        fontWeight = FontWeight(700),
                        color = TextColorBold,
                    )
                }

                Column(
                    modifier =
                        Modifier
                            .padding(10.dp),
                ) {
                    Text(
                        text = "Welcome back, please sign in to continue to your account",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = TextColorOne,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    CustomOutlinedTextField(
                        painterResource = painterResource(id = R.drawable.email),
                        lableValue = "Email",
                        placeholder = { Text(text = "email") },
                        keyboardOptions =
                            KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                            ),
                        onValueChange = { viewModel.setEmail(it) },
                        textValue = emailState.text,
                        isError = emailState.error != null,
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // password field
                    CustomOutlinedPasswordTextField(
                        passwordVisible = passwordVisible,
                        onValueChange = { viewModel.setPassword(it) },
                        onPasswordVisibilityChange = { isVisible ->
                            passwordVisible = isVisible
                        },
                        passwordState = passwordState.text,
                    )

                    Row(horizontalArrangement = Arrangement.Start) {
                        TextButton(
                            onClick = {
                                navController.navigateUp()
                                navController.navigateTo(Destinations.Register.route)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text =
                                    buildAnnotatedString {
                                        withStyle(
                                            style =
                                                SpanStyle(
                                                    color = TextColorOne,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight(500),
                                                ),
                                        ) {
                                            append(stringResource(id = R.string.forgot_password))
                                        }
                                    },
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center,
                                color = TextColorBold,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                    ) {
                        ButtonTextComponent(value = "Login", clickAction = {  navController.navigateTo(Destinations.Home.route)}, 280.dp)

                        Spacer(modifier = Modifier.height(30.dp))
                        DividerTextComponent()

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom,
                            modifier =
                                Modifier
                                    .fillMaxWidth(),
                        ) {
                            SocialButton(
                                icon = painterResource(id = R.drawable.google_48),
                                onClick = {},
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            SocialButton(
                                icon = painterResource(id = R.drawable.icons8_facebook_48),
                                onClick = {},
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        TextButton(
                            onClick = {
                                navController.navigateUp()
                                navController.navigateTo(Destinations.Home.route)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text =
                                    buildAnnotatedString {
                                        withStyle(
                                            style =
                                                SpanStyle(
                                                    color = TextColorOne,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight(400),
                                                ),
                                        ) {
                                            append(stringResource(id = R.string.dont_have_account))
                                        }
                                        append(" ")
                                        withStyle(
                                            style =
                                                SpanStyle(
                                                    color = TextColorBold,
                                                    fontWeight = FontWeight(700),
                                                    fontSize = 18.sp,
                                                ),
                                        ) {
                                            append(stringResource(id = R.string.register))
                                        }
                                    },
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center,
                                color = TextColorBold,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController: NavHostController = rememberNavController()
    val destinationsNavigator = DestinationsNavigator(navController)
    LoginScreen(destinationsNavigator)
}
