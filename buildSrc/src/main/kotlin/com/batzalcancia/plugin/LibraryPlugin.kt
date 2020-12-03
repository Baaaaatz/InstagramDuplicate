package com.batzalcancia.plugin

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import com.batzalcancia.plugin.configs.configureAndroid
import com.batzalcancia.plugin.configs.configureDependencies
import com.batzalcancia.plugin.configs.configureTestDependencies
import org.gradle.api.JavaVersion

open class LibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libraryExtension = target.extensions.getByType<LibraryExtension>()

        libraryExtension.configureLibrary()

        target.configureDependencies()
        target.configureTestDependencies()
    }


    private fun LibraryExtension.configureLibrary() {
        configureAndroid()

        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
                consumerProguardFiles("consumer-rules.pro")
            }
        }

        productFlavors {
            create("dev")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

    }

}