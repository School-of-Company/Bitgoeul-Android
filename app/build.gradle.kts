plugins {
    id("bitgoeul.android.application")
    id("bitgoeul.android.hilt")
}

android {
    namespace = "com.msg.bitgoeul_android"

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
        }
    }
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":feature:login"))
    implementation(project(":feature:sign-up"))
    implementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
}