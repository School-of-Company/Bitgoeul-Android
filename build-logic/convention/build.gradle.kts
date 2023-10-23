import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "bitgoeul.android.application"
            implementationClass = "com.msg.convention.AndroidApplicationConventionPlugin"
        }

        register("androidHilt") {
            id = "bitgoeul.android.hilt"
            implementationClass = "com.msg.convention.AndroidHiltConventionPlugin"
        }

        register("androidLint") {
            id = "bitgoeul.android.lint"
            implementationClass = "com.msg.convention.AndroidLintConventionPlugin"
        }

        register("androidCore") {
            id = "bitgoeul.android.core"
            implementationClass = "com.msg.convention.AndroidCoreConventionPlugin"
        }

        register("androidCompose") {
            id = "bitgoeul.android.compose"
            implementationClass = "com.msg.convention.AndroidComposeConventionPlugin"
        }

        register("jvmLibrary") {
            id = "bitgoeul.jvm.library"
            implementationClass = "com.msg.convention.JvmLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "bitgoeul.android.feature"
            implementationClass = "com.msg.convention.AndroidFeatureConventionPlugin"
        }
    }
}