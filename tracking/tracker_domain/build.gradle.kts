plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("kotlinx-serialization")
    alias(libs.plugins.dagger.hilt)
}
android {
    namespace = "com.codewithdipesh.tracker_domain"


}

apply(from = "$rootDir/base-module.gradle")

dependencies {


    implementation(project(Modules.core))


}