package com.saavatech.riserealestate.presentation.Register

import android.annotation.SuppressLint
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saavatech.riserealestate.DestinationsNavigator
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.components.AppBar
import com.saavatech.riserealestate.components.ButtonTextComponent
import com.saavatech.riserealestate.components.CustomOutlinedPasswordTextField
import com.saavatech.riserealestate.components.CustomOutlinedTextField
import com.saavatech.riserealestate.navigation.Destinations
import com.saavatech.riserealestate.presentation.viewModel.AuthViewModel
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navController: DestinationsNavigator,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val email = viewModel.emailState.value
    val name = viewModel.nameState.value
    val passwordState = viewModel.passwordState.value
    val mobileState = viewModel.mobileState.value
    val state = viewModel.loginState.value

    val snackbarHostState =
        remember {
            SnackbarHostState()
        }

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    // Observing events from the ViewModel
    LaunchedEffect(key1 = viewModel.eventFlow) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Indefinite,
                        withDismissAction = true,
                    )
                }
                is UiEvents.NavigationEvent -> {
                    // Handle navigation event
                    navController.navigateTo(event.route)
                }
            }
        }
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
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 20.dp),
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                Column(
                    modifier =
                        Modifier
                            .padding(horizontal = 16.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom,
                        modifier =
                            Modifier
                                .padding(vertical = 30.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.create_your_account),
                            fontSize = 25.sp,
                            fontWeight = FontWeight(400),
                            color = TextColorOne,
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = stringResource(id = R.string.account),
                            fontSize = 25.sp,
                            fontWeight = FontWeight(700),
                            color = TextColorBold,
                        )
                    }

                    Text(
                        text = "Welcome back, please sign up to create your account",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(500),
                        color = TextColorOne,
                    )
                }

                Spacer(modifier = Modifier.height(35.dp))
                CustomOutlinedTextField(
                    painterResource = painterResource(id = R.drawable.profile_round),
                    lableValue = "Full name",
                    placeholder = { Text(text = "Enter name") },
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                    onValueChange = { viewModel.setName(it) },
                    textValue = name.text,
                    isError = name.error != null,
                )
                // set last name error validation
                if (name.error != "") {
                    Text(
                        text = name.error ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))

                CustomOutlinedTextField(
                    painterResource = painterResource(id = R.drawable.email),
                    lableValue = "Email",
                    placeholder = { Text(text = "Enter email") },
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                        ),
                    onValueChange = { viewModel.setEmail(it) },
                    textValue = email.text,
                    isError = email.error != null,
                )
                // set email error validation
                if (email.error != "") {
                    Text(
                        text = email.error ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))

                CustomOutlinedTextField(
                    painterResource = painterResource(id = R.drawable.phonecall),
                    lableValue = "Mobile",
                    placeholder = { Text(text = "Enter phone number") },
                    keyboardOptions =
                        KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                        ),
                    onValueChange = { viewModel.setMobile(it) },
                    textValue = mobileState.text,
                    isError = mobileState.error != null,
                )
                // set email error validation
                if (mobileState.error != "") {
                    Text(
                        text = mobileState.error ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                // password field
                CustomOutlinedPasswordTextField(
                    passwordVisible = passwordVisible,
                    onValueChange = { viewModel.setPassword(it) },
                    onPasswordVisibilityChange = { isVisible ->
                        passwordVisible = isVisible
                    },
                    passwordState = passwordState.text,
                    isError = passwordState.error != null,
                )

                if (passwordState.error != "") {
                    Text(
                        text = passwordState.error ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    TextButton(
                        onClick = {
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
                                        append(stringResource(id = R.string.terms_of_service))
                                    }
                                },
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            color = TextColorBold,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    ButtonTextComponent(
                        value = "Register",
                        clickAction = { viewModel.signUpUser() },
                        280.dp,
                    )
                }
            }
        }
    }
}
