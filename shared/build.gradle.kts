import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ktlintKotlinter)
    alias(libs.plugins.kotlinSymbolProcessing)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = AppSettings.javaVersion.toString()
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.collections.immutable)
                implementation(libs.datetime)
                implementation(libs.sqldelight.runtime)
                implementation(libs.sqldelight.coroutineextension)
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
            dependencies {
                implementation(libs.collections.immutable)
                implementation(libs.sqldelight.androiddriver)
                api(libs.koin.core)
                api(libs.koin.android)
                api(libs.koin.annotations)
            }
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
        sourceCompatibility = AppSettings.javaVersion
        targetCompatibility = AppSettings.javaVersion
    }
}

allprojects {
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions.jvmTarget = AppSettings.javaVersion.toString()
    }

    tasks.withType(KaptGenerateStubsTask::class.java).configureEach {
        kotlinOptions.jvmTarget = AppSettings.javaVersion.toString()
    }

    tasks.lintKotlinCommonMain {
        exclude { it.file.path.contains("generated/") }
    }

    tasks.formatKotlinCommonMain {
        exclude { it.file.path.contains("generated/") }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.rayliu.commonmain.data.database")
            schemaOutputDirectory.set(
                file("src/commonMain/sqldelight/schema")
            )
            verifyMigrations.set(true)
        }
    }
}