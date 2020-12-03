package com.batzalcancia.dependencies

object Dependencies {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"

    // Navigation
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX =
        "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    // LiveData and ViewModel
    const val LIFECYCLE_EXTENSIONS =
        "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE}"
    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"

    // AndroidX
    const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.2"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.2.0-beta01"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.2.5"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.2.0-alpha06"
    const val DATASTORE = "androidx.datastore:datastore-preferences:1.0.0-alpha02"
    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_COROUTINE = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_KAPT = "androidx.room:room-compiler:${Versions.ROOM}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"

    // Material Components
    const val MATERIAL_COMPONENTS = "com.google.android.material:material:1.3.0-alpha02"

    // Coroutines
    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    const val COROUTINES_PLAY_SERVICES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.COROUTINES}"

    // Coil
    const val COIL = "io.coil-kt:coil:1.0.0"

    // Paging
    const val PAGING = "androidx.paging:paging-runtime:3.0.0-alpha07"

    //Shimmer
    const val SHIMMER = "com.facebook.shimmer:shimmer:0.5.0"

    //Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val RETROFIT_CONVERTER =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"

    //okhttp
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.0"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.0"

    //hilt
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.28-alpha"
    const val DAGGER_HILT_LIFECYCLE = "androidx.lifecycle:lifecycle-extensions:2.2.0"
    const val DAGGER_HILT_KAPT = "com.google.dagger:hilt-android-compiler:2.28-alpha"

    //hilt-viewmodels
    const val DAGGER_HILT_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"
    const val DAGGER_HILT_VIEWMODEL_KAPT = "androidx.hilt:hilt-compiler:1.0.0-alpha02"

    //kotlin serializer
    const val KOTLIN_SERIALIZER = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"
}