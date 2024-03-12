package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.domain.model.SignUpResult
import com.saavatech.riserealestate.domain.repository.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class SignUpUseCase
    @Inject
    constructor(private val repository: AuthRepository) {
        suspend operator fun invoke(
            type: String,
            address: String,
            name: String,
            email: String,
            mobile: String,
            password: String,
            aboutMe: String,
            latitude: String,
            longitude: String,
            facebookId: String,
            twitterId: String,
            profile: String,
            firebaseId: String,
        ): SignUpResult {
            val nameError = if (name.isBlank()) "Name cannot be blank" else null
            val addressError = if (address.isBlank()) "Address cannot be blank" else null
            val passwordError = if (password.isBlank()) "Password field cannot be blank" else null
            val emailError = if (email.isBlank()) "Email field cannot be blank" else null
            val mobileError = if (mobile.isBlank()) "Mobile number cannot be blank" else null

            if (nameError != null) {
                return SignUpResult(nameError = nameError)
            }

            if (addressError != null) {
                return SignUpResult(addressError = addressError)
            }

            if (passwordError != null) {
                return SignUpResult(passwordError = passwordError)
            }

            if (emailError != null) {
                Timber.tag("can return email errors").d(emailError)
                return SignUpResult(emailError = emailError)
            }

            if (mobileError != null) {
                return SignUpResult(mobileError = mobileError)
            }

//            prepare remote request data/object. this use requests from the data models
            val signUpRequest =
                SignUpRequest(
                    name = name.trim(),
                    email = email.trim(),
                    address = address.trim(),
                    mobile = mobile.trim(),
                    about_me = aboutMe.trim(),
                    latitude = latitude.trim(),
                    longitude = longitude.trim(),
                    facebook_id = facebookId.trim(),
                    twitter_id = twitterId.trim(),
                    profile = profile.trim(),
                    firebase_id = firebaseId.trim(),
                )

            val response = SignUpResult(result = repository.register(signUpRequest))

            Timber.tag("Sign uo response message:").d(response.result?.message)

            return response
        }
    }
