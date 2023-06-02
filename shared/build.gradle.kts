plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ktlintKotlinter)
    alias(libs.plugins.kotlinSymbolProcessing)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.collections.immutable)
                api(libs.koin.core)
                api(libs.koin.annotations)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.junit)
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.androidx.test.ext.junit)
                implementation(libs.espresso.core)
            }
        }
    }
}

android {
    namespace = "com.rayliu.commonmain"
    compileSdk = AppSettings.compileSdk

    defaultConfig {
        minSdk = AppSettings.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
}