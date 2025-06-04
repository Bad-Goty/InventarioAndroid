plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Plugin para serialización (opcional pero recomendado para type-safe navigation)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.example.inventario"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.inventario"
        minSdk = 26
        targetSdk = 35
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

    //Libreria para icono
    implementation(libs.androidx.material.icons.extended)

    //Navegacion
    implementation(libs.androidx.navigation.compose)

    // Retrofit para consumir APIs REST
    implementation (libs.retrofit)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp para el cliente HTTP (interceptores, logs, etc.)
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Gson para serialización JSON
    implementation ("com.google.code.gson:gson:2.10.1")

    // Coroutines para programación asíncrona
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // ViewModel para manejar el estado
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Para hacer llamadas HTTP desde ViewModels
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")


    implementation (libs.accompanist.systemuicontroller)

    implementation("com.google.accompanist:accompanist-navigation-animation:0.36.0")


    // CameraX para la cámara
    implementation ("androidx.camera:camera-camera2:1.3.1")
    implementation ("androidx.camera:camera-lifecycle:1.3.1")
    implementation ("androidx.camera:camera-view:1.3.1")

    // ML Kit para escanear QR
    implementation ("com.google.mlkit:barcode-scanning:17.2.0")

    // Permisos
    implementation ("com.google.accompanist:accompanist-permissions:0.32.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}