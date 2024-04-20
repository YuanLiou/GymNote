import org.gradle.api.JavaVersion

object AppSettings {
    const val compileSdk = 34
    const val targetSdk = 33
    const val minSdk = 26
    const val applicationId = "com.rayliu.gymnote"

    const val phoneVersionCode = 1
    const val phoneVersionName = "0.1"

    const val wearVersionCode = 1
    const val wearVersionName = "0.1"

    // check: https://developer.android.com/jetpack/androidx/releases/compose-kotlin
    const val kotlinCompilerExtensionVersion = "1.5.12"
    val javaVersion = JavaVersion.VERSION_17
}