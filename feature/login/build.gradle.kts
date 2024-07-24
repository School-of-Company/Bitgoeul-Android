plugins {
    id("bitgoeul.android.feature")
    id("bitgoeul.android.hilt")
}

android {
    namespace = "com.msg.login"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(project(":feature:main"))
}