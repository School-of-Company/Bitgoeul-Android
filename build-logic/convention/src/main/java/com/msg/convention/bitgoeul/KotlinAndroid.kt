package com.msg.convention.bitgoeul

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17

        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_17
        sourceCompatibility = JavaVersion.VERSION_17
    }
    configureJvm()
}

private fun Project.configureJvm() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.()-> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}