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