plugins {
    kotlin("multiplatform") version "2.0.0"
    id("com.android.application") version "8.3.0"
    id("org.jetbrains.compose") version "1.6.1"
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}

kotlin {
    androidTarget()
    jvm("desktop")
    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { iosTarget ->
        iosTarget.binaries.framework { baseName = "ComposeApp" }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation("androidx.room:room-runtime:2.6.1")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            
            // Koin
            implementation("io.insert-koin:koin-core:3.5.6")
            implementation("io.insert-koin:koin-compose:1.1.5")
        }
    }
}

android {
    namespace = "com.example.taskapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
}
