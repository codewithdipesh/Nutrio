plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.codewithdipesh.core_ui"

    compileOptions {
        compileOptions {
            // ...
            isCoreLibraryDesugaringEnabled = true // If you've explicitly enabled desugaring
            sourceCompatibility = JavaVersion.VERSION_18
            targetCompatibility = JavaVersion.VERSION_18
        }
    }
}

apply(from = "$rootDir/compose-module.gradle")

dependencies {

}