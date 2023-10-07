buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    }
}

plugins {
    kotlin("android") version "1.9.0" apply false
    kotlin("kapt") version "1.9.0"
    id("com.android.application") version "8.1.2" apply false
    id("com.android.library") version "8.1.2" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}

//allprojects {
//    repositories 0
//        mavenCentral()
//    }
//}
