package deps

object Dependencies {
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${DependenciesVersion.CORE_KTX}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersion.LIFE_CYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${DependenciesVersion.ACTIVITY_COMPOSE}"
    const val ANDROIDX_COMPOSE_BOM =
        "androidx.compose:compose-bom:${DependenciesVersion.COMPOSE_BOM}"
    const val ANDROIDX_UI = "androidx.compose.ui:ui"
    const val ANDROIDX_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val ANDROIDX_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val ANDROIDX_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val ANDROIDX_MATERIAL3 = "androidx.compose.material3:material3"

    const val hiltAndroid = "com.google.dagger:hilt-android:${DependenciesVersion.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${DependenciesVersion.HILT}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${DependenciesVersion.HILT}"
    const val hiltCompose = "androidx.hilt:hilt-work:${DependenciesVersion.HILT_COMPOSE}"
    const val hiltCompilerKapt = "androidx.hilt:hilt-compiler:${DependenciesVersion.HILT_COMPOSE}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${DependenciesVersion.HILT_COMPOSE}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersion.RETROFIT}"
    const val retrofitConverterGson =
        "com.squareup.retrofit2:converter-gson:${DependenciesVersion.RETROFIT}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${DependenciesVersion.OKHTTP}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${DependenciesVersion.OKHTTP}"

    const val roomRuntime = "androidx.room:room-runtime:${DependenciesVersion.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${DependenciesVersion.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${DependenciesVersion.ROOM}"
}