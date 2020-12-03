plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.batzalcancia.instagramduplicate.library")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project("::core"))
    implementation("androidx.camera:camera-camera2:1.0.0-beta12")
    implementation("androidx.camera:camera-lifecycle:1.0.0-beta12")
    implementation("androidx.camera:camera-view:1.0.0-alpha19")
    implementation("com.karumi:dexter:6.2.0")
}