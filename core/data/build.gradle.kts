import deps.hilt
import deps.okHttp
import deps.retrofit
import deps.testDebugDeps
import deps.testDeps
import deps.testImplDeps
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.example.data"
}

dependencies {
    okHttp()
    retrofit()
    hilt()

    testDeps()
    testImplDeps()
    testDebugDeps()
}
