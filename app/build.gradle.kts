plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.koleo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.koleo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Json
    implementation(libs.squareup.moshi.kotlin)

    // Retrofit
    implementation(libs.converter.moshi)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)

    //RxJava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.adapter.rxjava3)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3:1.3.9")

    //DI - hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Database - Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-rxjava3:2.6.1")

}

kapt {
    correctErrorTypes = true
}
