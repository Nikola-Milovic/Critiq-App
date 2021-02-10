allprojects {

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }

    plugins.apply(GradlePluginId.GRADLE_VERSION_PLUGIN)
}