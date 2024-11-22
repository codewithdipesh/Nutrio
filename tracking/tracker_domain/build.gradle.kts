plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.dagger.hilt)
}
android {
    namespace = "com.codewithdipesh.tracker_domain"


}

apply(from = "$rootDir/base-module.gradle")

dependencies {


    implementation(project(Modules.core))


}