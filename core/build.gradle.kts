plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.codewithdipesh.core"

    compileOptions {
        compileOptions {
            // ...
            isCoreLibraryDesugaringEnabled = true // If you've explicitly enabled desugaring
            sourceCompatibility = JavaVersion.VERSION_18
            targetCompatibility = JavaVersion.VERSION_18

        }
    }
}

apply(from = "$rootDir/base-module.gradle")

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
}