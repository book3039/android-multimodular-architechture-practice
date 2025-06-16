import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    id("org.jetbrains.dokka")
}

tasks {
    register<DokkaTask>("cDokkaHtml") {
        outputDirectory.set(file("${project.rootDir}/docs/${project.name}"))

        suppressObviousFunctions.set(false)
        suppressInheritedMembers.set(true)

        offlineMode.set(false)

        dokkaSourceSets {
            moduleName.set("ModularizationApp")
            configureEach {
                suppress.set(false)
                includeNonPublic.set(false)
                skipDeprecated.set(false)
                reportUndocumented.set(true)
                skipEmptyPackages.set(true)
                displayName.set("JVM")
                platform.set(org.jetbrains.dokka.Platform.jvm)
                samples.from("samples/basic.kt", "samples/advanced.kt")
                sourceRoots.from(file("src"))

                sourceLink {
                    localDirectory.set(file("src/main/kotlin"))

                    remoteUrl.set(
                        URL(
                            "https://github.com/book3039/android-multimodular-architechture-practice"
                        )
                    )

                    remoteLineSuffix.set("#L")
                }
                jdkVersion.set(8)
                noStdlibLink.set(false)
                noJdkLink.set(false)
                noAndroidSdkLink.set(false)

                externalDocumentationLink {
                    url.set(URL("https://github.com/book3039/android-multimodular-architechture-practice/docs"))
                }

                perPackageOption {
                    matchingRegex.set("kotlin($|\\.).*")
                    skipDeprecated.set(false)
                    reportUndocumented.set(true)
                    includeNonPublic.set(false)
                }

                perPackageOption {
                    matchingRegex.set(""".*\.internal.*""")
                    suppress.set(true)
                }

                suppressGeneratedFiles.set(false)
            }
        }
    }
}