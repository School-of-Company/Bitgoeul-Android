package com.msg.convention

import com.msg.convention.bitgoeul.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("org.jetbrains.kotlin.kapt")
            }

            dependencies {
                add("implementation", (libs.findLibrary("hilt.android").get()))
                add("kapt", (libs.findLibrary("hilt.compiler").get()))
                add("kaptAndroidTest", (libs.findLibrary("hilt.compiler").get()))
                add("kaptTest", (libs.findLibrary("hilt.compiler").get()))
            }
        }
    }
}