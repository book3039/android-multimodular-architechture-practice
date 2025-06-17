package extensions

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.LibraryBuildType
import org.gradle.api.Project
import java.nio.file.NoSuchFileException
import java.util.Properties

private const val LOCAL_PROPERTY_FILE_NAME = "dev_credentials.properties"

fun Project.getLocalProperty(propertyName: String) : String {
    val localProperties = Properties().apply {
        val localPropertiesFile = project.rootProject.file(LOCAL_PROPERTY_FILE_NAME)

        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName) ?: throw NoSuchFileException("Property not found: $propertyName")
}

fun ApplicationBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, value.toString())
}

fun ApplicationBuildType.buildConfigIntField(name: String, value: String) {
    this.buildConfigField("int", name, value)
}

fun ApplicationBuildType.buildConfigBooleanField(name: String, value: String) {
    this.buildConfigField("boolean", name, value)
}

fun LibraryBuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, value.toString())
}

fun LibraryBuildType.buildConfigIntField(name: String, value: String) {
    this.buildConfigField("int", name, value)
}

fun LibraryBuildType.buildConfigBooleanField(name: String, value: String) {
    this.buildConfigField("boolean", name, value)
}