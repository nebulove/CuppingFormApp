buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
    }
}

plugins {
    kotlin("android") version "1.8.21" apply false
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
//    id("org.jetbrains.kotlin.android") version "1.8.21" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}

//allprojects {
//    repositories {
//        mavenCentral()
//    }
//}
