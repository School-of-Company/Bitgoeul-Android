@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("bitgoeul.android.core")
    id("bitgoeul.android.compose")
}

android {
    namespace = "com.msg.ui"
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}