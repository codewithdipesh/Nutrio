plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
}
android {
    namespace = "com.codewithdipesh.core"

}

apply(from = "$rootDir/base-module.gradle")

dependencies {

}