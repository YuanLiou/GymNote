@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ktlintKotlinter)
}

android {
    namespace = AppSettings.applicationId
    compileSdk = AppSettings.compileSdk

    defaultConfig {
        applicationId = AppSettings.applicationId
        minSdk = AppSettings.minSdk
        targetSdk = AppSettings.targetSdk
        versionCode = AppSettings.phoneVersionCode
        versionName = AppSettings.phoneVersionName

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
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.play.services.wearable)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.collections.immutable)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    wearApp(project(":wear"))
}