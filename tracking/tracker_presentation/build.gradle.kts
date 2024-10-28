plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
}
android {
    namespace = "com.codewithdipesh.tracker_presentation"


}

apply(from = "$rootDir/compose-module.gradle")

dependencies {

   
    implementation(project(Modules.core))
    implementation(project(Modules.trackerDomain))

    implementation(Coil.coilCompose)


}