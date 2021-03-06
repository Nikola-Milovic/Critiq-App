plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_KAPT)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.HILT_ANDROID)
    id(GradlePluginId.GOOGLE_SERVICES)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        animationsDisabled = true
    }
}
dependencies {
    api(LibraryDependency.ANDROID_LEGACY_SUPPORT)
    api(LibraryDependency.LIFECYCLE_EXTENSIONS)
    api(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)

    api(LibraryDependency.TIMBER)
    api(LibraryDependency.NAVIGATION_FRAGMENT)
    api(LibraryDependency.NAVIGATION_UI)

    api(LibraryDependency.RECYCLER_VIEW)
    api(LibraryDependency.MATERIAL)

    api(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)

    implementation(LibraryDependency.HILT)
    kapt(LibraryDependency.HILT_COMPILER)

    //Google
    implementation(platform(PlatformDependency.FIREBASE_BOM))
    implementation(LibraryDependency.FIREBASE_MESSAGING)
    implementation(LibraryDependency.FIREBASE_ANALYTICS)

    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":feature_auth"))
    implementation(project(":feature_main"))
    implementation(project(":feature_upload"))
    implementation(project(":feature_my_posts"))
    implementation(project(":feature_post_detail"))

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.6")

    addTestDependencies()
}