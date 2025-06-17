package deps

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import test.TestDependencies

fun DependencyHandler.room() {
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterGson)
}

fun DependencyHandler.okHttp() {
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.hiltCompose)
    implementation(Dependencies.hiltNavigation)

    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAgp)
    kapt(Dependencies.hiltCompilerKapt)
}

fun DependencyHandler.androidx() {
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    addPlatform(Dependencies.ANDROIDX_COMPOSE_BOM)
    implementation(Dependencies.ANDROIDX_UI)
    implementation(Dependencies.ANDROIDX_UI_GRAPHICS)
    implementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    implementation(Dependencies.ANDROIDX_MATERIAL3)
}

fun DependencyHandler.testDeps() {
    testImplementation(TestDependencies.JUNIT)
}

fun DependencyHandler.testImplDeps() {
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT_EXT)
    androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.ANDROIDX_UI_TEST_JUNIT4)
}

fun DependencyHandler.testDebugDeps() {
    debugImplementation(Dependencies.ANDROIDX_UI_TOOLING)
    debugImplementation(TestDependencies.ANDROIDX_UI_TEST_MANIFEST)
}



fun DependencyHandler.loginModule() {
    moduleImplementation(project(":features:login"))
}

fun DependencyHandler.homeModule() {
    moduleImplementation(project(":features:home"))
}

fun DependencyHandler.dataModule() {
    moduleImplementation(project(":core:data"))
}

fun DependencyHandler.domainModule() {
    moduleImplementation(project(":core:domain"))
}

fun DependencyHandler.presentationModule() {
    moduleImplementation(project(":core:presentation"))
}