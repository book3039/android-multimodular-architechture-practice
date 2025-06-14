package builds

sealed class Build {
    open val isMinifyEnabled = false
    open val enableUnitTestCoverage = false
    open val isDebuggable = false
    open val applicationIdSuffix = ""
    open val versionNameSuffix = ""

    object Debug : Build() {
        override val versionNameSuffix = "-DEBUG"
        override val applicationIdSuffix = ".debug"
        override val isMinifyEnabled = false
        override val isDebuggable = true
        override val enableUnitTestCoverage = true
    }

    object ReleaseExternalQA : Build() {
        override val versionNameSuffix = "-QA"
        override val applicationIdSuffix = ".releaseExternalQA"
        override val isMinifyEnabled = false
        override val isDebuggable = true
        override val enableUnitTestCoverage = true
    }

    object Release : Build() {
        override val isMinifyEnabled = true
        override val isDebuggable = false
        override val enableUnitTestCoverage = false
    }
}