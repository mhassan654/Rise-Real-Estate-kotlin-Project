package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.common.TextFieldState
import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.data.local.AppPreferences
import com.saavatech.riserealestate.domain.use_case.SignUpUseCase
import com.saavatech.riserealestate.presentation.AuthState
import com.saavatech.riserealestate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
    @Inject
    constructor(
        private val signUpUseCase: SignUpUseCase,
        private val preferences: AppPreferences,
    ) : ViewModel() {
        private val _eventFlow = MutableSharedFlow<UiEvents>()
        val eventFlow = _eventFlow.asSharedFlow()

        private var _loginState = mutableStateOf(AuthState())
        val loginState: State<AuthState> = _loginState

        private val _nameState = mutableStateOf(TextFieldState())
        val nameState: State<TextFieldState> = _nameState

        fun setName(value: String) {
            _nameState.value = nameState.value.copy(text = value)
        }

        private val _emailState = mutableStateOf(TextFieldState())
        val emailState: State<TextFieldState> = _emailState

        fun setEmail(value: String) {
            _emailState.value = emailState.value.copy(text = value)
        }

        private val _passwordState = mutableStateOf(TextFieldState())
        val passwordState: State<TextFieldState> = _passwordState

        fun setPassword(value: String) {
            _passwordState.value = passwordState.value.copy(text = value)
        }

//        private val _address = mutableStateOf(TextFieldState())
//        val address: State<TextFieldState> = _address
//
//        fun setAddress(value: String) {
//            _address.value = address.value.copy(text = value)
//        }
//
        private val _mobileState = mutableStateOf(TextFieldState())
        val mobileState: State<TextFieldState> = _mobileState

        fun setMobile(value: String) {
            _mobileState.value = mobileState.value.copy(text = value)
        }

        fun signUpUser() {
            Timber.tag("signup check")
            viewModelScope.launch {
                _loginState.value = loginState.value.copy(isLoading = true)

                val signUpResult =
                    signUpUseCase(
                        name = nameState.value.text,
                        email = emailState.value.text,
                        password = passwordState.value.text,
                        mobile = mobileState.value.text,
                        type = "mobile",
                        firebase_id = "346543ytrytr",
                    )

                _loginState.value = loginState.value.copy(isLoading = false)

                if (signUpResult.emailError != null || signUpResult.nameError != null || signUpResult.passwordError != null) {
                    if (signUpResult.emailError != null) {
                        _emailState.value = emailState.value.copy(error = signUpResult.emailError)
                        Timber.d("Email error: ${signUpResult.emailError}")
                    }

                    if (signUpResult.nameError != null) {
                        _nameState.value = nameState.value.copy(error = signUpResult.nameError)
                        Timber.d("Name error: ${signUpResult.nameError}")
                    }

                    if (signUpResult.passwordError != null) {
                        _passwordState.value = passwordState.value.copy(error = signUpResult.passwordError)
                        Timber.d("Password error: ${signUpResult.passwordError}")
                    }
                }

                when (signUpResult.result) {
                    is Resource.Success -> {
                        Timber.tag("view model message:").d(
                            signUpResult.result.data
                                ?.message
                                .toString(),
                        )
                        val result = signUpResult.result
                        result.data?.let { preferences.saveAuthToken(it.token) }
                        Timber.tag("token value").d(preferences.getAuthToken())
                        result.data?.let { preferences.saveUserData(it.data) }

                        Timber.tag("user value").d(preferences.getUserData().toString())

                        _eventFlow.emit(
                            UiEvents.SnackbarEvent(
                                signUpResult.result.data?.message ?: "Success!",
                            ),
                        )
                        delay(500)
                        _eventFlow.emit(
                            UiEvents.NavigationEvent("Home"),
                            // HomeScreenDestination.route
                        )
                    }
                    is Resource.Error -> {
                        Timber.tag("error model message:").d(
                            signUpResult.result.message.toString(),
                        )
                        _eventFlow.emit(
                            UiEvents.SnackbarEvent(
                                signUpResult.result.message ?: "Error!",
                            ),
                        )
                    }
                    else -> {
                        UiEvents.SnackbarEvent(
                            signUpResult.result?.data?.message ?: "Error!",
                        )
                    }
                }
            }
        }
    }
