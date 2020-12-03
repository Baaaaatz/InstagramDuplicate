buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.10"))
        classpath(kotlin("serialization", version = "1.4.10"))
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
