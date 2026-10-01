plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "ai.digitalfuture.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "ai.digitalfuture.app"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
    }

    flavorDimensions += "platform"

    productFlavors {
        create("nexora") {
            dimension = "platform"
            applicationId = "ai.digitalfuture.nexora"
            manifestPlaceholders["appName"] = "digitalfuture.ai — NEXORA-Digital"
            manifestPlaceholders["appIcon"] = "@drawable/nexora_logo"
        }
        create("sakan") {
            dimension = "platform"
            applicationId = "ai.digitalfuture.sakan"
            manifestPlaceholders["appName"] = "digitalfuture.ai — SAKAN"
            manifestPlaceholders["appIcon"] = "@drawable/sakan_logo"
        }
        create("helpme") {
            dimension = "platform"
            applicationId = "ai.digitalfuture.helpme"
            manifestPlaceholders["appName"] = "digitalfuture.ai — HELP-ME"
            manifestPlaceholders["appIcon"] = "@drawable/help_me_logo"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.09.00"))
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.zxing:core:3.5.3")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
