plugins {
    id("bitgoeul.android.feature")
    id("bitgoeul.android.hilt")
}

android {
    namespace = "com.msg.lecture"
}

dependencies {
    implementation(libs.kakao.maps)
}