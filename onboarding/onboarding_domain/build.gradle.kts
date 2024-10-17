plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.codewithdipesh.onboarding_domain"

}

apply(from = "$rootDir/base-module.gradle")

dependencies {
    implementation(project(Modules.core))
}