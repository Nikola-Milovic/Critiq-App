@file:Suppress("detekt.StringLiteralDuplication")

private object LibraryVersion {
    const val TIMBER = "4.7.1"
    const val PLAY_CORE = "1.7.3" //164
    const val RECYCLER_VIEW = "1.2.0-alpha05"
    const val MATERIAL = "1.1.0-alpha09"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val LIFECYCLE = "2.2.0"
    const val LIFECYCLE_VIEW_MODEL_KTX = "2.2.0"
    const val ANDROID_LEGACY_SUPPORT = "1.0.0"

    //Koin
    const val RETROFIT = "2.9.0"
    const val SWIPE_REFRESH_LAYOUT = "1.1.0"
    const val LOTTIE_ANIMATION = "3.5.0"
    const val FACEBOOK_SDK = "8.1.0"
    const val GOOGLE_LOCATION = "17.1.0"
    const val DEXTER = "6.2.2"
    const val IMAGE_PICKER = "2.4.1"
    const val GLIDE = "4.9.0"
    const val GSON = "2.8.6"

    const val ROOM = "2.3.0-beta02"
    const val FIREBASE_BOM = "26.2.0"

    const val VOLLEY = "1.1.1"

    const val RXJAVA = "3.0.10"
    const val RXJAVA_ANDROID = "3.0.0"
}


object LibraryDependency {
    const val TIMBER = "com.jakewharton.timber:timber:${LibraryVersion.TIMBER}"
    //UI
    const val SUPPORT_CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
    const val MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    //LifeCycle
    const val LIFECYCLE_EXTENSIONS =
            "androidx.lifecycle:lifecycle-extensions:${LibraryVersion.LIFECYCLE}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE_VIEW_MODEL_KTX}"
    const val ANDROID_LEGACY_SUPPORT =
            "androidx.legacy:legacy-support-v4:${LibraryVersion.ANDROID_LEGACY_SUPPORT}"

    const val NAVIGATION_FRAGMENT =
            "androidx.navigation:navigation-fragment:${CoreVersion.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui:${CoreVersion.NAVIGATION}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${LibraryVersion.RETROFIT}"

    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${LibraryVersion.SWIPE_REFRESH_LAYOUT}"

    const val LOTTIE_ANIMATION = "com.airbnb.android:lottie:${LibraryVersion.LOTTIE_ANIMATION}"

    const val FACEBOOK_SDK = "com.facebook.android:facebook-login:${LibraryVersion.FACEBOOK_SDK}"

    const val GOOGLE_LOCATION = "com.google.android.gms:play-services-location:${LibraryVersion.GOOGLE_LOCATION}"

    const val DEXTER = "com.karumi:dexter:${LibraryVersion.DEXTER}"

    const val IMAGE_PICKER = "com.github.esafirm.android-image-picker:imagepicker:${LibraryVersion.IMAGE_PICKER}"
    const val GLIDE = "com.github.bumptech.glide:glide:${LibraryVersion.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${LibraryVersion.GLIDE}"

    const val GSON = "com.google.code.gson:gson:${LibraryVersion.GSON}"

    const val ROOM = "androidx.room:room-runtime:${LibraryVersion.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${LibraryVersion.ROOM}"
    const val ROOM_RXJAVA = "androidx.room:room-rxjava3:${LibraryVersion.ROOM}"

    const val HILT = "com.google.dagger:hilt-android:${CoreVersion.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${CoreVersion.HILT}"

    const val RXJAVA = "io.reactivex.rxjava3:rxjava:${LibraryVersion.RXJAVA}"
    const val RXJAVA_ANDROID = "io.reactivex.rxjava3:rxandroid:${LibraryVersion.RXJAVA_ANDROID}"
    const val RXJAVA_RETROFIT_ADAPTER = "com.squareup.retrofit2:adapter-rxjava3:${LibraryVersion.RETROFIT}"
}