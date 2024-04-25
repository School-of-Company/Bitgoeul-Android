@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("bitgoeul.android.core")
    id("bitgoeul.android.hilt")
}

android {
    namespace = "com.msg.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

}