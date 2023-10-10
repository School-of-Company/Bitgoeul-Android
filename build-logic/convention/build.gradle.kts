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
    }
}