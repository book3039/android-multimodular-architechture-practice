package test

import deps.DependenciesVersion

object TestDependencies {
    const val JUNIT = "junit:junit:${DependenciesVersion.JUNIT}"
    const val ANDROIDX_JUNIT_EXT = "androidx.test.ext:junit:${DependenciesVersion.JUNIT_VERSION}"
    const val ANDROIDX_ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${DependenciesVersion.ESPRESSO}"
    const val ANDROIDX_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:1.8.2"
    const val ANDROIDX_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest:1.8.2"
}