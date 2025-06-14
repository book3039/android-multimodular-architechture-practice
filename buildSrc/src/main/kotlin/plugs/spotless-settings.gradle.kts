import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

@Suppress("INACCESSIBLE_TYPE")
configure<com.diffplug.gradle.spotless.SpotlessExtension> {

    format("xml") {
        target("**/*.xml")
        prettier(mapOf("prettier" to "2.7.1", "@prettier/plugin-xml" to "2.2.0"))
            .config(
                mapOf(
                    "parser" to "xml",
                    "tabWidth" to 4,
                    "printWidth" to 80,
                    "useTabs" to false,
                    "semi" to true,
                    "singleQuote" to false,
                    "attributeSortOrder" to arrayOf("name", "id", "type"),
                    "selfClosingTags" to arrayOf("br", "img")
                )
            )
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.50.0")
            .userData(mapOf("android" to "true", "max_line_length" to "120"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }

    java {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.java"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        googleJavaFormat()
    }

    kotlinGradle {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.gradle.kts", "*.gradle.kts"),
                    "exclude" to listOf("**/build/**")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.50.0")
            .userData(mapOf("android" to "true"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }

    tasks.named("preBuild") {
        dependsOn("spotlessCheck")
    }

    tasks.named("preBuild") {
        dependsOn("spotlessApply")
    }
}