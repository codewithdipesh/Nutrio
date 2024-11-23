plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("kotlinx-serialization")
    alias(libs.plugins.dagger.hilt)
}
android {
    namespace = "com.codewithdipesh.tracker_presentation"


}

apply(from = "$rootDir/compose-module.gradle")

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))
    implementation(project(Modules.coreUi))
    implementation(Coil.coilCompose)


}