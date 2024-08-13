package com.saavatech.riserealestate.domain.use_case

import com.saavatech.riserealestate.data.remote.request.SignUpRequest
import com.saavatech.riserealestate.domain.model.SignUpResult
import com.saavatech.riserealestate.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) {
        suspend operator fun invoke(
            name: String,
            email: String,
            password: String,
            type: String,
            firebase_id: String,
            mobile: String,
        ): SignUpResult {
//            mobile: String,
//            type: String,
//            address: String,
//            aboutMe: String,
//            latitude: String,
//            longitude: String,
//            facebookId: String,
//            twitterId: String,
//            profile: String,
//            firebaseId: String,
            val nameError = if (name.isBlank()) "Name cannot be blank" else null
//            val addressError = if (address.isBlank()) "Address cannot be blank" else null
            val passwordError = if (password.isBlank()) "Password field cannot be blank" else null
            val emailError = if (email.isBlank()) "Email field cannot be blank" else null
            val mobileError = if (mobile.isBlank()) "Mobile number cannot be blank" else null

            if (passwordError != null) {
                return SignUpResult(passwordError = passwordError)
            }
            if (emailError != null) {
                return SignUpResult(emailError = emailError)
            }

            if (nameError != null) {
                return SignUpResult(nameError = nameError)
            }

//            if (addressError != null) {
//                return SignUpResult(addressError = addressError)
//            }
//
            if (mobileError != null) {
                return SignUpResult(mobileError = mobileError)
            }

//            prepare remote request data/object. this use requests from the data models
            val signUpRequest =
                SignUpRequest(
                    name = name.trim(),
                    email = email.trim(),
                    password = password.trim(),
                    type = type.trim(),
                    firebase_id = firebase_id.trim(),
                    mobile = mobile.trim(),
                )

//            address = address.trim(),
//            mobile = mobile.trim(),
//            about_me = aboutMe.trim(),
//            latitude = latitude.trim(),
//            longitude = longitude.trim(),
//            facebook_id = facebookId.trim(),
//            twitter_id = twitterId.trim(),
//            profile = profile.trim(),
//            firebase_id = firebaseId.trim(),

            val res = SignUpResult(result = repository.register(signUpRequest))
            return res
        }
    }
