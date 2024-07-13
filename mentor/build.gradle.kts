plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    id ("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.solutionteam.mentor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.solutionteam.mentor"
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
                "proguard-rules.pro"
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.material.icons.core)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)


    //room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.androidx.room.ktx)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //accompanist
    implementation(libs.accompanist.systemuicontroller)

    //time
    implementation(libs.kotlinx.datetime)

    //coil
    implementation(libs.coil.compose)

    //lottie
    implementation(libs.lottie.compose)
    // DataStore
    implementation(libs.datastore.preferences)

    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation (libs.firebase.auth.ktx)
    implementation (libs.play.services.auth)
    implementation (libs.androidx.lifecycle.runtime.compose)

    //Gimien AI
    implementation(libs.generativeai)

    api(project(":design_system"))

    //SplashScreen Api
    implementation(libs.androidx.core.splashscreen)

    //constraint layout
    implementation(libs.androidx.constraintlayout.compose)
}
