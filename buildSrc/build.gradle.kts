plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
    implementation("com.android.tools.build:gradle:3.6.3")

    implementation(gradleApi())
    implementation(localGroovy())
}
