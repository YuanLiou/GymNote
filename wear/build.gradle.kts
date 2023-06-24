import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ktlintKotlinter)
    alias(libs.plugins.kotlinSymbolProcessing)
}

android {
    namespace = AppSettings.applicationId
    compileSdk = AppSettings.compileSdk

    defaultConfig {
        applicationId = AppSettings.applicationId
        minSdk = AppSettings.minSdk
        targetSdk = AppSettings.targetSdk
        versionCode = AppSettings.wearVersionCode
        versionName = AppSettings.wearVersionName
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

    applicationVariants.all {
        val variantName = name
        sourceSets {
            getByName("main") {
                java.srcDir(File("build/generated/ksp/$variantName/kotlin"))
            }
        }
    }

    compileOptions {
        sourceCompatibility = AppSettings.javaVersion
        targetCompatibility = AppSettings.javaVersion
    }
    kotlinOptions {
        jvmTarget = AppSettings.javaVersion.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppSettings.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        htmlOutput = file("lint-report.html")
        textReport = true
    }
}

allprojects {
    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions.jvmTarget = AppSettings.javaVersion.toString()
    }

    tasks.withType(KaptGenerateStubsTask::class.java).configureEach {
        kotlinOptions.jvmTarget = AppSettings.javaVersion.toString()
    }

    tasks.lintKotlinMain {
        exclude { it.file.path.contains("generated/")}
    }

    tasks.formatKotlinMain {
        exclude { it.file.path.contains("generated/")}
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.core.ktx)
    implementation(libs.play.services.wearable)
    implementation(libs.percentlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.recyclerview)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.wear.compose.material)
    implementation(libs.wear.compose.foundation)
    implementation(libs.wear.compose.navigation)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.horologist.composables)
    implementation(libs.horologist.compose.layout)

    // kotlinx
    implementation(libs.collections.immutable)
    implementation(libs.datetime)

    // koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.navigation)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    wearApp(project(":wear"))
    lintChecks(libs.slack.compose.linter)
}