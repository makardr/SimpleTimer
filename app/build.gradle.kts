plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.simpletimer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.simpletimer"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.simpletimer.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


val hilt_version = "2.48.1"
val coroutines_version = "1.7.3"


dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //    Room database
    implementation ("androidx.room:room-runtime:2.6.0")
    ksp ("androidx.room:room-compiler:2.6.0")

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.6.0")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:$hilt_version")
    ksp ("com.google.dagger:hilt-android-compiler:$hilt_version")


    // Local unit tests
    testImplementation ("androidx.test:core:1.5.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("com.google.truth:truth:1.1.5")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.1")
    testImplementation ("io.mockk:mockk:1.13.8")
    testImplementation ("com.google.dagger:hilt-android-testing:$hilt_version")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")


// Instrumentation tests
    androidTestImplementation ("com.google.dagger:hilt-android-testing:$hilt_version")
    kspAndroidTest ("com.google.dagger:hilt-android-compiler:$hilt_version")
    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("com.google.truth:truth:1.1.5")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test:core-ktx:1.5.0")
    androidTestImplementation ("com.squareup.okhttp3:mockwebserver:4.9.1")
    androidTestImplementation ("io.mockk:mockk-android:1.13.8")
    androidTestImplementation ("androidx.test:runner:1.5.2")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")

}