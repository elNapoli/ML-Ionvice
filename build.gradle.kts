buildscript {
    dependencies {
        classpath(libs.google.services)
    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.googleDaggerHilt) version libs.versions.hiltAndroid apply false
    alias(libs.plugins.googleService) version libs.versions.googleServices apply false
}