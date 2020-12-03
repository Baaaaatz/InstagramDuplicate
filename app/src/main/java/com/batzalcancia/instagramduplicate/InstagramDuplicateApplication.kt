package com.batzalcancia.instagramduplicate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InstagramDuplicateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("Hello")
    }
}