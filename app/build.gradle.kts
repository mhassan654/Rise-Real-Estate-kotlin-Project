plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt") // version "1.9.0"

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.saavatech.riserealestate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.saavatech.riserealestate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("io.coil-kt:coil-svg:2.6.0")
    implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    val navVersion = "2.7.7"

    implementation("androidx.navigation:navigation-compose:$navVersion")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.airbnb.android:lottie:6.3.0")
    implementation("com.google.accompanist:accompanist-pager:0.35.0-alpha")
    implementation("androidx.core:core-splashscreen:1.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material:material:1.6.3")
    // extra project dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")

    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")

    implementation("androidx.compose.material:material-icons-extended:1.6.3")
    implementation("androidx.hilt:hilt-navigation:1.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0-RC2")

    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation("androidx.test.espresso:espresso-core:3.5.1")

//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    kapt("androidx.hilt:hilt-compiler:1.2.0")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("com.google.accompanist:accompanist-pager:0.35.0-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.35.0-alpha")

    //    coil
    implementation("io.coil-kt:coil-compose:2.5.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
