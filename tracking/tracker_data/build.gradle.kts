import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.codewithdipesh.tracker_data"
    buildFeatures{
        buildConfig = true
    }
    defaultConfig{
        val keystoreFile = project.rootProject.file("local.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val app_id = properties.getProperty("app_id") ?: ""
        val app_key = properties.getProperty("app_key") ?: ""
        val url = properties.getProperty("url") ?: ""

        buildConfigField(
            type = "String",
            name = "APP_ID",
            value = app_id
        )
        buildConfigField(
            type = "String",
            name = "APP_KEY",
            value = app_key
        )
        buildConfigField(
            type = "String",
            name = "URL",
            value = url
        )
    }

}

apply(from = "$rootDir/base-module.gradle")

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.GsonConverter)

    "kapt"(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

}