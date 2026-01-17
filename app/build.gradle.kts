plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // 🔥 THIS LINE MUST BE HERE
    id("com.google.gms.google-services")

}

android {
    namespace = "com.jaydeep.zonerun"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.jaydeep.zonerun"
        minSdk = 30
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)



    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))

    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    //maps
    implementation("com.google.maps.android:maps-compose:4.3.3")






    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

// Navigation
    implementation("androidx.navigation:navigation-compose:2.8.0")

// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")

// Location (later)
    implementation("com.google.android.gms:play-services-location:21.3.0")

// Maps (later)
    implementation("com.google.maps.android:maps-compose:4.3.3")

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compilercommon)
    implementation(libs.androidx.compose.foundation.layout)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


}