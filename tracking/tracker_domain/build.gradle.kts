plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.codewithdipesh.tracker_domain"

    compileOptions {
        compileOptions {
            // ...
            isCoreLibraryDesugaringEnabled = true // If you've explicitly enabled desugaring

        }
    }
}

apply(from = "$rootDir/base-module.gradle")

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    implementation(project(Modules.core))


}