plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.messangermix"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.messangermix"
        minSdk = 24
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
}

dependencies {


// Adding Firebase
    implementation(platform(libs.firebase.bom))

// Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:34.2.0"))
// Firebase dep for Aut lib
    implementation("com.google.firebase:firebase-auth")

// Firebase real time DB
    implementation("com.google.firebase:firebase-database")


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}