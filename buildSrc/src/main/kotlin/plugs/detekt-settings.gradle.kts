import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

val DETEKT_VERSION = "1.23.3"
apply<DetektPlugin>()

configure<DetektExtension> {
    toolVersion = DETEKT_VERSION
    source.from("src/main/java", "src/main/kotlin")
    parallel = false
    config.from("${rootProject.projectDir}/detekt/detekt-config.yml")
    buildUponDefaultConfig = false
    allRules = false
    baseline = file("${rootProject.projectDir}/detekt/detekt-baseline.xml")
    disableDefaultRuleSets = false
    debug = true
    ignoreFailures = true
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("huawei")
    ignoredVariants = listOf("googleRelease")
    basePath = projectDir.absolutePath
}

tasks.withType<Detekt>() {
    include("**/*.kt", "**/*.kts")
    exclude(
        "**/build/**",
        ".*/resources/.*",
        ".*test.*",
        ".*/tmp/.*",
        "**/generated/**"
    )
    reports {
        xml {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/detekt-report.xml"))
        }
        html {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/detekt-report.html"))
        }
        sarif {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/detekt-report.sarif"))
        }
        md {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/detekt-report.md"))
        }
        txt {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/detekt-report.txt"))
        }
    }
    jvmTarget = JavaVersion.VERSION_17.toString()
    dependencies {
        "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${DETEKT_VERSION}")
    }
}

tasks.registering(ReportMergeTask::class) {
    group = "reporting"
    description = "Merges all detekt reports into one report file"
    dependsOn("detekt")
}

tasks.named("detekt") {
    dependsOn("detektBaseline")
    dependsOn(":features:login:detektBaseline")
}

tasks.named("preBuild") {
    dependsOn("detekt")
}