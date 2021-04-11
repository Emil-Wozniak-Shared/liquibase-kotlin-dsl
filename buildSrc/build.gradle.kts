plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    gradlePluginPortal()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.4.30")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}