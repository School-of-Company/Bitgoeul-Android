@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("bitgoeul.jvm.library")
}

dependencies {
    implementation(libs.okhttp.logging)
}