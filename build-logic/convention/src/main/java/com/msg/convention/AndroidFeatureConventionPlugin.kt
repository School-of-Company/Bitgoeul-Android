package com.msg.convention

import com.msg.convention.bitgoeul.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("bitgoeul.android.core")
                apply("bitgoeul.android.hilt")
                apply("bitgoeul.android.compose")
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:design-system"))
                add("implementation", project(":core:domain"))

                add("implementation", libs.findLibrary("coil.kt").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
            }
        }
    }
}