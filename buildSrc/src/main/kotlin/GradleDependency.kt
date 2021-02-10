object GradlePluginVersion {
    const val ANDROID_GRADLE = "6.1.1"
    const val KTLINT_GRADLE = "9.2.1"
    const val DETEKT = "1.9.1"
    const val GRADLE_VERSION_PLUGIN = "0.22.0"
    const val SAFE_ARGS = CoreVersion.NAVIGATION
}

object GradlePluginId {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val GRADLE_VERSION_PLUGIN = "com.github.ben-manes.versions"
    const val SAFE_ARGS = "androidx.navigation.safeargs.kotlin"

    const val KOTLIN_KAPT = "kotlin-kapt"
}

object GradleOldWayPlugins {
    const val ANDROID_GRADLE =
            "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val SAFE_ARGS =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersion.SAFE_ARGS}"
}
