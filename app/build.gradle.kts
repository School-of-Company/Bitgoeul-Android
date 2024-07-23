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
    implementation(project(":core:common"))
    implementation(project(":core:design-system"))
    implementation(project(":core:datastore"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:design-system"))
    implementation(project(":feature:login"))
    implementation(project(":feature:sign-up"))
    implementation(project(":feature:student-activity"))
    implementation(project(":feature:lecture"))
    implementation(project(":feature:my-page"))
    implementation(project(":feature:post"))
    implementation(project(":feature:club"))
    implementation(project(":feature:email"))
    implementation(project(":feature:main"))
    implementation(project(":feature:certification"))

    implementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
}