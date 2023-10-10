package com.msg.convention

import com.android.build.api.dsl.ApplicationExtension
import com.msg.convention.bitgoeul.configureKotlinAndroid
import com.msg.convention.bitgoeul.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("bitgoeul.android.lint")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig {
                    applicationId = "com.msg.bitgoeul_android"
                    minSdk = 26
                    targetSdk = 34
                    versionCode = 1
                    versionName = "1.0"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    vectorDrawables.useSupportLibrary = true
                }

                buildFeatures.compose = true

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.3"
                }

                dependencies {
                    add("implementation", libs.findBundle("kotlinx-coroutines").get())
                    add("implementation", libs.findBundle("compose").get())
                }
            }

        }

    }
}