package com.saavatech.riserealestate.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saavatech.riserealestate.common.TextFieldState
import com.saavatech.riserealestate.common.UiEvents
import com.saavatech.riserealestate.domain.use_case.SignUpUseCase
import com.saavatech.riserealestate.presentation.AuthState
import com.saavatech.riserealestate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
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

        private val _address = mutableStateOf(TextFieldState())
        val address: State<TextFieldState> = _address

        fun setAddress(value: String) {
            _address.value = address.value.copy(text = value)
        }

        private val _mobile = mutableStateOf(TextFieldState())
        val mobile: State<TextFieldState> = _mobile

        fun setMobile(value: String) {
            _mobile.value = mobile.value.copy(text = value)
        }

        fun signUpUser() {
            Timber.tag("register")
            viewModelScope.launch {
                _loginState.value = loginState.value.copy(isLoading = true)

                val signUpResult =
                    signUpUseCase(
                        name = nameState.value.text,
                        email = emailState.value.text,
                        address = address.value.text,
                        mobile = mobile.value.text,
                        aboutMe = mobile.value.text,
                        facebookId = mobile.value.text,
                        twitterId = mobile.value.text,
                        profile = mobile.value.text,
                        firebaseId = mobile.value.text,
                        latitude = mobile.value.text,
                        longitude = mobile.value.text,
                        password = passwordState.value.text,
                        type = mobile.value.text,
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
                        _eventFlow.emit(
                            UiEvents.NavigationEvent("Home"), // HomeScreenDestination.route
                        )
                    }
                    is Resource.Error -> {
                        UiEvents.SnackbarEvent(
                            signUpResult.result.message ?: "Error!",
                        )
                    }
                    else -> {
                        UiEvents.SnackbarEvent(
                            signUpResult.result?.message ?: "Error!",
                        )
                    }
                }
            }
        }
    }
