plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.batzalcancia.instagramduplicate.library")
}

android {
    defaultConfig {
        versionName = "1.0.0"
    }
    productFlavors {
        getByName("dev") {
            dimension = "environment"
            versionCode = 1
            versionNameSuffix = "-dev+1"
        }

    }
    buildTypes {
        getByName("debug") {
            versionNameSuffix = "+debug"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


