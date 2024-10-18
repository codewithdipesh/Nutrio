plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.codewithdipesh.onboarding_presentation"


}

apply(from = "$rootDir/compose-module.gradle")

dependencies {

    implementation(project(Modules.core))
    implementation (project(Modules.coreUi))
    implementation(project(Modules.onboardingDomain))

    //exoplayer
    implementation(Media3.exoplayer)
    implementation(Media3.UI)
    implementation(Media3.session)

    //vicoChart
    implementation(VicoChart.compose)
    implementation(VicoChart.m2)
    implementation(VicoChart.m3)
    implementation(VicoChart.core)
    implementation(VicoChart.views)
}