@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("bitgoeul.android.core")
    id("bitgoeul.android.compose")
    id("bitgoeul.android.lint")
}

android {
    namespace = "com.msg.design_system"
}

dependencies {
    implementation(libs.coil.kt.compose)
}