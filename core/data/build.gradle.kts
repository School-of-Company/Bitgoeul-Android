@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("bitgoeul.android.core")
    id("bitgoeul.android.hilt")
}

android {
    namespace = "com.msg.data"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
}