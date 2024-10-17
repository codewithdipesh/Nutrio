plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.codewithdipesh.core_ui"

}

apply(from = "$rootDir/compose-module.gradle")

dependencies {

}