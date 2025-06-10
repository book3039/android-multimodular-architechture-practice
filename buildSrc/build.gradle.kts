plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    api(kotlin("gradle-plugin:2.0.0"))
    implementation("com.android.tools.build:gradle:8.7.0")
}